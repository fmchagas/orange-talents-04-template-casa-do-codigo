package br.com.fmchagas.api.categoria;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonCreator.Mode;

import br.com.fmchagas.api.shared.validation.UniqueField;

public class CategoriaRequest {
	
	@NotBlank
	@UniqueField(clazzEntity = Categoria.class, fieldName = "nome")
	private String nome;
	
	@JsonCreator(mode = Mode.PROPERTIES) // corrige B.O do JSON parse error, quando contrutor tem um parametro
	public CategoriaRequest(@NotBlank String nome) {
		this.nome = nome.trim();
	}
	
	public Categoria toModel() {
		return new Categoria(nome);
	}
}
