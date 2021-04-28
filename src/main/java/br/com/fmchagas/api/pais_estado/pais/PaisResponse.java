package br.com.fmchagas.api.pais_estado.pais;

public class PaisResponse {
	private String nome;
	
	public PaisResponse(Pais pais) {
		nome = pais.getNome();
	}
	
	public String getNome() {
		return nome;
	}
}
