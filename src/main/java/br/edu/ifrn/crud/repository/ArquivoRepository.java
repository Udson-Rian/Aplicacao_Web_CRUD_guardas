package br.edu.ifrn.crud.repository;
/** Interface do repositório da entidade Arquivo 
* @author Udson Rian Monteiro Bandeira
* @author Dianna Ellen Costa Santos
* @author Vitor Kauã Faustino Gomes
* @version 1.0
*/
import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifrn.crud.dominio.Arquivo;

public interface ArquivoRepository extends JpaRepository<Arquivo, Long>{

}
