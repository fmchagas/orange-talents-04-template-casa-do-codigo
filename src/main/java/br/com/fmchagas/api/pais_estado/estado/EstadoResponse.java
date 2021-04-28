package br.com.fmchagas.api.pais_estado.estado;

public class EstadoResponse {
	private String nome;
	
	public EstadoResponse(Estado estado) {
		nome = estado.getNome();
	}

	public String getNome() {
		return nome;
	}
}
