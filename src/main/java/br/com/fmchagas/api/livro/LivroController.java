package br.com.fmchagas.api.livro;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fmchagas.api.autor.AutorRepository;
import br.com.fmchagas.api.categoria.CategoriaRepository;


@RestController
@RequestMapping("/api/v1/livros")
public class LivroController {
	
	private AutorRepository autorRepository;
	private CategoriaRepository categoriaRepository;
	private LivroRepository livroRepository;

	public LivroController(AutorRepository autorRepository, CategoriaRepository categoriaRepository,
			LivroRepository livroRepository) {
		this.autorRepository = autorRepository;
		this.categoriaRepository = categoriaRepository;
		this.livroRepository = livroRepository;
	}
	
	@PostMapping
	public ResponseEntity<?> adiciona(@Valid @RequestBody LivroRequest request){
		final Livro livro = request.toModel(autorRepository, categoriaRepository);
		final Livro livroSalvo = livroRepository.save(livro);
		
		final LivroResponse response = new LivroResponse(livroSalvo);
		
		return ResponseEntity.ok(response);
	}
}
