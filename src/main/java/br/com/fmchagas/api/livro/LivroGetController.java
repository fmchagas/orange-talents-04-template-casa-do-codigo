package br.com.fmchagas.api.livro;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class LivroGetController {

	private LivroRepository livroRepository;

	public LivroGetController(LivroRepository livroRepository) {
		this.livroRepository = livroRepository;
	}
	
	@GetMapping("/api/v1/livros")
	public ResponseEntity<List<LivroNomeEIdResposne>> lista() {
		final List<Livro> livros = (List<Livro>) livroRepository.findAll();
		
		final List<LivroNomeEIdResposne> response = LivroNomeEIdResposne.converter(livros);
		
		return ResponseEntity.ok(response);
	}
}
