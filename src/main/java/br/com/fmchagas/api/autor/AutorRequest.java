package br.com.fmchagas.api.autor;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import br.com.fmchagas.api.shared.validation.UniqueField;

public class AutorRequest {
	@NotBlank
	private String nome;
	
	@NotBlank @Email
	@UniqueField(clazzEntity = Autor.class, fieldName = "email")
	private String email;
	
	@NotBlank @Size(max = 400)
	private String descricao;
	
	public AutorRequest(@NotBlank String nome, @NotBlank @Email String email, @NotBlank @Max(400) String descricao) {
		this.nome = nome;
		this.email = email;
		this.descricao = descricao;
	}

	public Autor toModel() {
		return new Autor(nome, email, descricao);
	}
}
