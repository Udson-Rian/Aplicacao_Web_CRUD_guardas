package br.edu.ifrn.crud.repository;
/** Interface do repositório da entidade NivelEscolar. 
* @author Udson Rian Monteiro Bandeira
* @author Dianna Ellen Costa Santos
* @author Vitor Kauã Faustino Gomes
* @version 1.0
*/
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.edu.ifrn.crud.dominio.NivelEscolar;

public interface NivelEscolarRepository extends JpaRepository<NivelEscolar, Integer>{

	// Notação responsável por fazer a busca no Banco de dados através da linguagem HQL
	@Query("select nv from NivelEscolar nv where nv.nome like %:nome% ")
	
	/** Método responsável por receber os dados da busca de acordo com 
	 * o parâmetro passado.  
	 * @param nome - Variável que armazena o nome que será buscado.
	 * @return List<NivelEscolar> - Retorna uma lista com os Níveis escolares encontrados.
	 */
	List<NivelEscolar> findByNome(@Param ("nome") String nome);
	
}
