package br.com.fmchagas.api.livro;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LivroDetalheController {
	
	private LivroRepository livroRepository;

	public LivroDetalheController(LivroRepository livroRepository) {
		this.livroRepository = livroRepository;
	}
	
	@GetMapping("/api/v1/livros/{id}")
	public ResponseEntity<LivroDetalheResponse> detalhe(@PathVariable Long id) {
		Assert.notNull(id, "ops, o id está nulo ou sem valor, id: " + id);
		
		Optional<Livro> possivelAutor = livroRepository.findById(id);
		
		if(possivelAutor.isEmpty())
			return ResponseEntity.notFound().build();
		
		final LivroDetalheResponse response = new LivroDetalheResponse(possivelAutor.get());
		
		
		return ResponseEntity.ok(response);
	}
}
