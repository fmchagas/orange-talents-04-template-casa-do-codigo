package br.com.fmchagas.api.autor;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "autor")
public class Autor {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(length = 128 , nullable = false)
	private @NotBlank String nome;
	
	@Column(length = 128 , nullable = false)
	private @NotBlank @Email String email;
	
	@Column(length = 400 , nullable = false)
	private @NotBlank @Size(max = 400) String descricao;
	
	@Column(name = "data_criacao", nullable = false)
	private @NotNull LocalDateTime dataCriacao = LocalDateTime.now();
	
	
	/**
	 * @Deprecated - Construtor único para ORM Hibernate
	 */
	@Deprecated
	public Autor() {}
	
	public Autor(@NotBlank String nome, @NotBlank @Email String email, @NotBlank @Size(max = 400) String descricao) {
		this.nome = nome;
		this.email = email;
		this.descricao = descricao;
	}

	public String getNome() {
		return nome;
	}
	
	public String getEmail() {
		return email;
	}
}