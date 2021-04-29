package br.com.fmchagas.api.pais_estado.estado;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

import org.springframework.util.Assert;

import br.com.fmchagas.api.pais_estado.pais.Pais;

@Entity
public class Estado {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(length = 64, nullable = false, unique = true)
	private @NotBlank String nome;
	
	private @ManyToOne Pais pais;
	
	/**
	 * @Deprecated - Construtor único para ORM Hibernate
	 */
	@Deprecated
	public Estado() {}
	
	public Estado(@NotBlank String nome, Pais pais) {
		this.nome = nome;
		this.pais = pais;
	}

	public String getNome() {
		return nome;
	}
	
	private boolean pertenceAoPais(Pais pais) {
		Assert.notNull(pais, "Não podemos ter uma país nulo para fazer a comparação");
		
		return this.pais.equals(pais);
	}

	public boolean naoPertenceAoPais(Pais pais) {
		Assert.notNull(pais, "Não podemos ter uma país nulo para fazer a comparação");
		return !pertenceAoPais(pais);
	}
}
