package br.edu.ifrn.crud.repository;
/** Interface do repositório da entidade Guarda. 
* @author Udson Rian Monteiro Bandeira
* @author Dianna Ellen Costa Santos
* @author Vitor Kauã Faustino Gomes
* @version 1.0
*/
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.edu.ifrn.crud.dominio.Guarda;

public interface GuardaRepository extends JpaRepository<Guarda, Integer>{
	
	// Notação responsável por fazer a busca no Banco de dados através da linguagem HQL
	@Query("select g from Guarda g where g.email like %:email% "
			+ "and g.nome like %:nome%")
	
	/** Método responsável por receber os dados da busca de acordo com 
	 * os parâmetros passados.  
	 * @param email - Variável que armazena o email que será buscado.
	 * @param nome - Variável que armazena o nome que será buscado.
	 * @return List<Guarda> - Retorna uma lista com os guardas encontrados.
	 */
	List<Guarda> findByEmailAndNome(@Param("email") String email, 
									@Param("nome") String nome);
	
}
