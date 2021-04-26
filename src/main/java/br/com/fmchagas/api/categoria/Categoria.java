package br.com.fmchagas.api.categoria;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "categoria")
public class Categoria {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private @NotBlank String nome;
	
	/**
	 * @Deprecated - Construtor único para ORM Hibernate
	 */
	@Deprecated
	public Categoria() {}

	public Categoria(@NotBlank String nome) {
		this.nome = nome;
	}
	
	public String getNome() {
		return nome;
	}
}
