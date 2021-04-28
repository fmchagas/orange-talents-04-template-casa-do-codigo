package br.com.fmchagas.api.pais_estado.pais;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Entity
public class Pais {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(length = 64, nullable = false, unique = true)
	private @NotBlank String nome;
	
	/**
	 * @Deprecated - Construtor único para ORM Hibernate
	 */
	@Deprecated
	public Pais() {}

	public Pais(@NotBlank String nome) {
		this.nome = nome;
	}
	
	public String getNome() {
		return nome;
	}
}
