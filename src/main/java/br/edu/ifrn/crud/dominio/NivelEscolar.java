package br.edu.ifrn.crud.dominio;
/** Classe da entidade NivelEscolar 
* @author Udson Rian Monteiro Bandeira
* @author Dianna Ellen Costa Santos
* @author Vitor Kauã Faustino Gomes
* @version 1.2
*/
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class NivelEscolar {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false)
	private String nome;

	// O período da linha 26 a linha 38 estão os métodos Get's e Set's da classe.

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
	
}
