package br.edu.ifrn.crud.controladores;
/** Classe controladora do incio 
 * @author Udson Rian Monteiro Bandeira
 * @author Dianna Ellen Costa Santos
 * @author Vitor Kauã Faustino Gomes
 * @version 1.0
 * 
*/

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class InicioController {
	
	/** O método "inicio" retorna a URL "/" para que a página inicio.html 
	 * seja chamada.
	 * @return String - nome inicio
	 */
	@GetMapping("/")
	public String inicio() {
		return "inicio";
	}
	
}
