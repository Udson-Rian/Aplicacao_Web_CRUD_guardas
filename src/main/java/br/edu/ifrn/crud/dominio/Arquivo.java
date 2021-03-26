package br.edu.ifrn.crud.dominio;
  /** Classe da entidade Arquivo 
 * @author Udson Rian Monteiro Bandeira
 * @author Dianna Ellen Costa Santos
 * @author Vitor Kauã Faustino Gomes
 * @version 1.0
*/
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
public class Arquivo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String nomeArquivo;
	
	private String tipoArquivo;
	
	@Lob
	@Basic(fetch = FetchType.LAZY)
	private byte[] dados;
	
	/** Construtor que permite que todos os parametros sejam "setados".
	 * @param id - Id do arquivo no Banco de dados.
	 * @param nomeArquivo - Nome do arquivo.
	 * @param tipoArquivo - Tipo do arquivo. (ex.: .jpg, .png etc.)
	 * @param dados - Tamanho do arquivo.
	 */
	public Arquivo(Long id, String nomeArquivo, String tipoArquivo, byte[] dados) {
		super();
		this.id = id;
		this.nomeArquivo = nomeArquivo;
		this.tipoArquivo = tipoArquivo;
		this.dados = dados;
	}
	
	// Construtor da classe Arquivo
	public Arquivo() {
	}
	
	// O período da linha 54 a linha 84 estão todos os Get's e Set's da classe.
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomeArquivo() {
		return nomeArquivo;
	}

	public void setNomeArquivo(String nomeArquivo) {
		this.nomeArquivo = nomeArquivo;
	}

	public String getTipoArquivo() {
		return tipoArquivo;
	}

	public void setTipoArquivo(String tipoArquivo) {
		this.tipoArquivo = tipoArquivo;
	}

	public byte[] getDados() {
		return dados;
	}

	public void setDados(byte[] dados) {
		this.dados = dados;
	}
	
}
