package br.com.fmchagas.api.pais_estado.pais;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.fmchagas.api.shared.validation.UniqueField;

public class PaisRequest {
	
	@NotBlank @UniqueField(clazzEntity = Pais.class, fieldName = "nome")
	private String nome;

	public PaisRequest(@JsonProperty("nome") String nome) {
		this.nome = nome.trim();
	}
	
	public Pais toModel() {
		return new Pais(nome);
	}
}
