package br.edu.ifrn.crud.controladores;
/** Classe controladora da Busca 	 
 * @author Udson Rian Monteiro Bandeira
 * @author Dianna Ellen Costa Santos
 * @author Vitor Kauã Faustino Gomes
 * @version 1.2
 * 
*/
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.edu.ifrn.crud.dominio.Guarda;
import br.edu.ifrn.crud.repository.GuardaRepository;

@Controller
@RequestMapping("/usuarios")
public class BuscaGuardaController {

	@Autowired
	private GuardaRepository guardaRepository;
	
	/** Método responsável por entrar na busca de guardas
	 * @return String - retorna página de busca de guardas
	 */
	@GetMapping("/busca")
	public String entrarBusca() {
		return "usuario/busca";
	}
	
	/** Método responsável por realizar a busca do guarda por meio 
	 * do nome ou email presentes no cadastro.
	 * 
	 * @param nome - Passa o nome que foi digitado para realizar a busca.
	 * @param email - Passa o email que foi digitado para realizar a busca.
	 * @param mostrarTodosDados - Variável que recebe valor booleano para 
	 * saber ser o usuário deseja exibir todos os dados do usuário buscado.
	 * @param sessao - Armazena os dados da busca na memória do servidor.
	 * @param model - Responsável por se comunicar e passar valores para
	 * a página html.
	 * @return String - Retorna a página de busca de guardas.
	 */
	@GetMapping("/buscar")
	public String buscar(
				 @RequestParam(name="nome", required=false) String nome,
				 @RequestParam(name="email", required=false) String email,
				 @RequestParam(name="mostrarTodosDados", required=false) 
				 	Boolean mostrarTodosDados,
				 HttpSession sessao,
				 ModelMap model
			) {
		
		List<Guarda> usuariosEncontrados = 
				guardaRepository.findByEmailAndNome(email, nome);
		
		model.addAttribute("usuariosEncontrados", usuariosEncontrados);
		
		if(mostrarTodosDados != null) {
			model.addAttribute("mostrarTodosDados", true);
		}
		
		return "usuario/busca";
	}
	
	/** Método reponsável pela função de remover um guarda 
	 * @param idUsuario - Recebe o id do guarda no qual a função 
	 * "remover" foi clicada.
	 * @param sessao - Armazena o id desse guarda na memória do servidor.
	 * @param attr - Emite uma mensagem de sucesso caso a operação seja bem 
	 * sucedida.
	 * @return String - Retorna o redirecionamento para a página de busca.
	 */
	@GetMapping("/remover/{id}")
	public String remover(
				@PathVariable("id") Integer idUsuario,
				HttpSession sessao, 
				RedirectAttributes attr
			) {
		
		guardaRepository.deleteById(idUsuario);
		attr.addFlashAttribute("msgSucesso", "Guarda removido com sucesso!");
		
		return "redirect:/usuarios/buscar";
	}

}
