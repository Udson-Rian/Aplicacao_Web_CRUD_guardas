package br.edu.ifrn.crud;
/** Classe Application 
* @author Udson Rian Monteiro Bandeira
* @author Dianna Ellen Costa Santos
* @author Vitor Kauã Faustino Gomes
* @version 1.0
*/
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

	/** Método responsável por executar a aplicação.
	 * @param args - Tudo que está presente dentro do método main.
	 */
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
