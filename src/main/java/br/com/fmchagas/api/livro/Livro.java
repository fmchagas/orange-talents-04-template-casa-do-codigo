package br.com.fmchagas.api.livro;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.fmchagas.api.autor.Autor;
import br.com.fmchagas.api.categoria.Categoria;

@Entity
@Table(name = "livro")
public class Livro {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(length = 128, nullable = false)
	private @NotBlank String titulo;
	
	@Column(length = 500, nullable = false)
	private @NotBlank @Size(max = 500) String resumo;
	
	@Column(columnDefinition = "TEXT")
	private String sumario;
	
	private @NotNull @Min(20) BigDecimal valor;
	
	private @NotNull @Min(100) Integer numeroPagina;
	
	@Column(length = 16, nullable = false)
	private @NotBlank String isbn;
	
	private @NotNull @Future LocalDateTime dataPublicacao;
	
	@ManyToOne
	private Categoria categoria;
	
	@ManyToOne
	private Autor autor;
	
	/**
	 * @Deprecated - Construtor único para ORM Hibernate
	 */
	@Deprecated
	public Livro() {}

	public Livro(@NotBlank String titulo, @NotBlank @Size(max = 500) String resumo, String sumario,
			@NotNull @Min(20) BigDecimal valor, @NotNull @Min(100) Integer numeroPagina, @NotBlank String isbn,
			@NotNull @Future LocalDateTime dataPublicacao, Categoria categoria, Autor autor) {
				this.titulo = titulo;
				this.resumo = resumo;
				this.sumario = sumario;
				this.valor = valor;
				this.numeroPagina = numeroPagina;
				this.isbn = isbn;
				this.dataPublicacao = dataPublicacao;
				this.categoria = categoria;
				this.autor = autor;
	}
	
	public String getTitulo() {
		return titulo;
	}
	
	public String getIsbn() {
		return isbn;
	}
	
	public Integer getNumeroPagina() {
		return numeroPagina;
	}
	
	public BigDecimal getValor() {
		return valor;
	}
}
