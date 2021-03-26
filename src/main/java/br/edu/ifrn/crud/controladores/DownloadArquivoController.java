package br.edu.ifrn.crud.controladores;
/** Classe controladora do Download de Arquivo
 * @author Udson Rian Monteiro Bandeira
 * @author Dianna Ellen Costa Santos
 * @author Vitor Kauã Faustino Gomes
 * @version 1.0
 * 
*/
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import br.edu.ifrn.crud.dominio.Arquivo;
import br.edu.ifrn.crud.repository.ArquivoRepository;
import jdk.jfr.ContentType;

@Controller
public class DownloadArquivoController {

	@Autowired
	private ArquivoRepository arquivoRepository;
	
	/** Método reponsável por fazer o download do arquivo(caso o usuário deseje).
	 * @param idArquivo - Identifica o id do arquivo em questão.
	 * @param salvar - Variável que determina se o usuário deseja ou não salvar o 
	 * arquivo que está sendo submetido.
	 * @return ResponseEntity - Retorna uma resposta, onde não se sabe ao certo o que 
	 * conterá, mas que dirá se arquivo será apenas exibido ou será feito download.
	 */
	@GetMapping("/download/{idArquivo}")
	public ResponseEntity<?> downloadFile(
				@PathVariable Long idArquivo,
				@PathParam("salvar") String salvar
			){
		//carregando arquivo do banco de dados
		Arquivo arquivoBD = arquivoRepository.findById(idArquivo).get();
		
		String texto = (salvar == null || salvar.equals("true")) ? 
				 			"attachment; filename=\"" + arquivoBD.getNomeArquivo() + "\""
				 			: "inline; filename=\"" + arquivoBD.getNomeArquivo() + "\"";
		
		return ResponseEntity.ok()
				.contentType(MediaType.parseMediaType(arquivoBD.getTipoArquivo()))
				.header(HttpHeaders.CONTENT_DISPOSITION, texto)
				.body(new ByteArrayResource(arquivoBD.getDados()));
	}
	
	
	
}
