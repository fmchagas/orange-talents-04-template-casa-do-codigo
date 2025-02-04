package br.com.fmchagas.api.livro;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.util.Assert;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import br.com.fmchagas.api.autor.Autor;
import br.com.fmchagas.api.autor.AutorRepository;
import br.com.fmchagas.api.categoria.Categoria;
import br.com.fmchagas.api.categoria.CategoriaRepository;
import br.com.fmchagas.api.shared.validation.ExistsId;
import br.com.fmchagas.api.shared.validation.UniqueField;

public class LivroRequest {
	
	@NotBlank @UniqueField(clazzEntity = Livro.class, fieldName = "titulo")
	private String titulo;
	
	@NotBlank @Size(max = 500)
	private String resumo;
	
	private String sumario;
	
	@NotNull @Min(value = 20)
	private BigDecimal valor;
	
	@NotNull @Min(value = 100)
	private Integer numeroPagina;
	
	@NotBlank @UniqueField(clazzEntity = Livro.class, fieldName = "isbn")
	private String isbn;
	
	/*
	 * @JsonFormat passando a dataPublicacao pelo construtor o jackson não está consegindo serializar para padão pt-BR
	 * caso saiba uma forma de passar essa dataPublicacao pelo construtor e serializar para padrão pt-BR, 
	 * favor sinta-se livre para fazer e caso goste de ajudar pode me chamar e mostrar como faz
	 */
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss", shape = Shape.STRING)
	@NotNull @Future
	private LocalDateTime dataPublicacao;
	
	@NotNull @ExistsId(clazzEntity = Categoria.class, fieldName = "id")
	private Long categoriaId;

	@NotNull @ExistsId(clazzEntity = Autor.class, fieldName = "id")
	private Long autorId;

	public LivroRequest(@NotBlank String titulo, @NotBlank  @Size(max = 500) String resumo, String sumario,
			@NotNull @Min(20) BigDecimal valor, @NotNull @Min(100) Integer numeroPagina, @NotBlank String isbn,
			@NotNull Long categoriaId, @NotNull Long autorId) {

		this.titulo = titulo;
		this.resumo = resumo;
		this.sumario = sumario;
		this.valor = valor;
		this.numeroPagina = numeroPagina;
		this.isbn = isbn;
		this.categoriaId = categoriaId;
		this.autorId = autorId;
	}

	public Livro toModel(AutorRepository autorRepository, CategoriaRepository categoriaRepository) {
		Optional<Categoria> categoria = categoriaRepository.findById(categoriaId);
		Optional<Autor> autor = autorRepository.findById(autorId);
		
		Assert.state(!categoria.isEmpty(), "A categoria não existe no banco, categoriaId: " + categoriaId);
		Assert.state(!autor.isEmpty() , "O autor não existe no banco, autorId: " + autorId);
		
		return new Livro(titulo, resumo, sumario, valor, numeroPagina, isbn, dataPublicacao, categoria.get(), autor.get());
	}
}
