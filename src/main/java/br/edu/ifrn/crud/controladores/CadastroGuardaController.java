package br.edu.ifrn.crud.controladores;
/** Classe controladora do cadastro de Guardas 
 * @author Udson Rian Monteiro Bandeira
 * @author Dianna Ellen Costa Santos
 * @author Vitor Kauã Faustino Gomes
 * @version 1.2
 */

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.edu.ifrn.crud.dominio.NivelEscolar;
import br.edu.ifrn.crud.dominio.Arquivo;
import br.edu.ifrn.crud.dominio.Guarda;
import br.edu.ifrn.crud.dto.AutocompleteDTO;
import br.edu.ifrn.crud.repository.NivelEscolarRepository;
import br.edu.ifrn.crud.repository.ArquivoRepository;
import br.edu.ifrn.crud.repository.GuardaRepository;

@Controller
@RequestMapping("/usuarios")
public class CadastroGuardaController {
	
	@Autowired
	private GuardaRepository guardaRepository;

	@Autowired
	private NivelEscolarRepository nivelRepository;

	@Autowired
	private ArquivoRepository arquivoRepository;
	
	/**Método para chamar a página html reponsável por 
	 * efetuar o cadastro do guarda
	 * @param model - Concatena com o "th:object" presente na página de cadastro
	 * @return String - nome da página html
	 */
	@GetMapping("/cadastro")
	public String EntrarCadastro(ModelMap model) {
		model.addAttribute("guarda", new Guarda());
		return "usuario/cadastro";
	}
	
	/**Método responsável pela validação dos dados 
	 * @param usuario - Passa os dados da classe Guarda
	 * @return List<String> - mensagens que serão exibidas caso houver
	 * algum campo não preenchido.
	 */
	private List<String> validarDados(Guarda usuario){
		
		List<String> msgs = new ArrayList<>();
		
		if(usuario.getNome() == null || usuario.getNome().isEmpty()) {
			msgs.add("O campo NOME é obrigatório!");
		}
		if(usuario.getEmail() == null || usuario.getEmail().isEmpty()) {
			msgs.add("O campo EMAIL é obrigatório!");
		}
		if(usuario.getSenha() == null || usuario.getSenha().isEmpty()) {
			msgs.add("O campo SENHA é obrigatório!");
		}
		if(usuario.getSexo() == null || usuario.getSexo().isEmpty()) {
			msgs.add("O campo SEXO é obrigatório!");
		}
		
		return msgs;
	}
	
	/** Método utilizado para salvar os dados que um usuário cadastrar 
	 * preenchendo as lacunas de um formlário
	 * @param guarda - passa os parâmetros da classe Guarda
	 * @param model - utilizado para passar valores que serão vizualizados 
	 * na página da aplicação 
	 * @param arquivo - passa os parâmetros da classe Arquivo
	 * @param attr - usado para enviar mensagens para a página html
	 * @param sessao - armazena os dados passados na memória do servidor 
	 * @return String - retorna de volta a página de cadastro
	 */
	@PostMapping("/salvar")
	@Transactional(readOnly=false)
	public String salvar(Guarda guarda, ModelMap model,
							@RequestParam("file") MultipartFile arquivo, 
							RedirectAttributes attr, 
							HttpSession sessao) {
		
		//enviando as mensagens de erro para o html
		List<String> msgValidação = validarDados(guarda);
				
		if(!msgValidação.isEmpty()) {
			model.addAttribute("msgErro", msgValidação);
				return "usuario/cadastro";
		}
		
		try {
			
			if(arquivo != null && !arquivo.isEmpty()) {
				//normalizando nome do arquivo
				String nomeArquivo = 
						StringUtils.cleanPath(arquivo.getOriginalFilename());
				
				Arquivo arquivoBD = 
						new Arquivo(null, nomeArquivo, 
								arquivo.getContentType(),
								arquivo.getBytes());
				
				//salvando a foto no banco de dados
				arquivoRepository.save(arquivoBD);	
				
				if(guarda.getFoto() != null && guarda.getFoto().getId() != null
						&& guarda.getFoto().getId() > 0) {
					arquivoRepository.delete(guarda.getFoto());
				}
				
				guarda.setFoto(arquivoBD);
			} else {
				guarda.setFoto(null);
			}
			
			//serve para cadastro e edição
			guardaRepository.save(guarda);
			attr.addFlashAttribute("msgSucesso", "Operação realizada com sucesso!");
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return "redirect:/usuarios/cadastro";
	}
	
	/** Método utilizado para editar os dados de um usuário já cadastrado
	 * @param idUsuario - recebe o id do guarda que os dados serão editados
	 * @param model - utilizado para passar valores que serão vizualizados 
	 * na página da aplicação
	 * @param sessao - armazena os dados passados na memória do servidor
	 * @return String - retorna a página de cadastro 
	 */
	@GetMapping("/editar/{id}")
	public String iniciarEdicao(
				@PathVariable("id") Integer idUsuario,
				ModelMap model,
				HttpSession sessao
			) {
		
		Guarda u = guardaRepository.findById(idUsuario).get();
		
		model.addAttribute("guarda", u);
		
		return "/usuario/cadastro";
	}
	
	/** Método responsável pelo funcionamento do AutoComplete presente
	 * na página de cadastro
	 * @param termo - recebe o termo utilizado na busca do autocomplete
	 * @return List<> - retorna uma lista com o resultados encontrados na busca
	 */
	@GetMapping("/autocompleteProfissoes")
	@Transactional(readOnly = true )
	@ResponseBody
	public List<AutocompleteDTO> autocompleteProfissoes(
						@RequestParam("term") String termo){
		
		List<NivelEscolar> profissoes = 
				nivelRepository.findByNome(termo);
		List<AutocompleteDTO> resultados = new ArrayList<>();
		
		profissoes.forEach(p -> resultados.add(
					new AutocompleteDTO(p.getNome(), p.getId())
				));
		
		return resultados;
	}
}
