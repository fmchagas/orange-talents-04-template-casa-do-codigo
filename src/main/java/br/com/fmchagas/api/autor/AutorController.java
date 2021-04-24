package br.com.fmchagas.api.autor;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/autores")
public class AutorController {
	
	private AutorRepository autorRepository;

	public AutorController(AutorRepository autorRepository) {
		this.autorRepository = autorRepository;
	}
	
	
	@PostMapping
	@Transactional
	public ResponseEntity<AutorResponse> adiciona(@Valid @RequestBody AutorRequest request) {
		final Autor autor = autorRepository.save(request.toModel());
		
		final AutorResponse autorResponse = new AutorResponse(autor);
		
		return ResponseEntity.ok(autorResponse);
	}
}