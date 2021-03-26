package br.edu.ifrn.crud.dto;
/** Classe da entidade Arquivo 
* @author Udson Rian Monteiro Bandeira
* @author Dianna Ellen Costa Santos
* @author Vitor Kauã Faustino Gomes
* @version 1.0
*/
public class AutocompleteDTO {

	private String label;
	
	private Integer value;
	
	/** Construtor que permite o acesso a classe AutoCompleteDTO
	 * @param label - Variável que armazena o nome que foi buscado.
	 * @param value - Variável que armazena o valor da mesma no Banco de dados.
	 */
	public AutocompleteDTO(String label, Integer value) {
		super();
		this.label = label;
		this.value = value;
	}

	// O período da linha 26 a linha 38 estão os métodos Get's e Set's da classe.
	
	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}
	
}
