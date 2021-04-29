package br.com.fmchagas.api.cliente;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.util.Assert;

import br.com.fmchagas.api.pais_estado.estado.Estado;
import br.com.fmchagas.api.pais_estado.pais.Pais;

@Entity
public class Cliente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(length = 128, nullable = false, unique = true)
	private @NotBlank @Email @Size(max = 128) String email;
	
	@Column(length = 32, nullable = false)
	private @NotBlank @Size(min = 3, max = 32) String nome;
	
	@Column(length = 64, nullable = false)
	private @NotBlank @Size(min = 2, max = 64) String sobrenome;
	
	@Column(length = 14, nullable = false, unique = true)
	private @NotBlank String documento;
	
	@Column(length = 128, nullable = false)
	private @NotBlank @Size(max = 128) String endereco;
	
	@Column(length = 32, nullable = false)
	private @NotBlank @Size(max = 32) String complemento;
	
	@Column(length = 64, nullable = false)
	private @NotBlank @Size(max = 64) String cidade;
	
	@Column(length = 15, nullable = false)
	private @NotBlank @Size(max = 15) String telefone;
	
	@Column(length = 8, nullable = false)
	private @NotBlank @Size(max = 8) String cep;
	
	@ManyToOne @JoinColumn(nullable = false)
	private @NotNull Pais pais;
	
	@ManyToOne
	private Estado estado;

	public Cliente(@NotBlank @Email String email, @NotBlank @Size(min = 3, max = 32) String nome,
			@NotBlank @Size(min = 2, max = 64) String sobrenome, @NotBlank String documento,
			@NotBlank @Size(max = 128) String endereco, @NotBlank @Size(max = 32) String complemento,
			@NotBlank @Size(max = 64) String cidade, @NotBlank @Size(max = 15) String telefone,
			@NotBlank @Size(max = 8) String cep, @NotNull Pais pais) {
				this.email = email;
				this.nome = nome;
				this.sobrenome = sobrenome;
				this.documento = documento;
				this.endereco = endereco;
				this.complemento = complemento;
				this.cidade = cidade;
				this.telefone = telefone;
				this.cep = cep;
				this.pais = pais;
	}
	
	public void setEstado(Estado estado) {
		Assert.notNull(estado, "Você está tentando fazer a associoação de um estado que não existe");
		this.estado = estado;
	}

	public Long getId() {
		return id;
	}
}
