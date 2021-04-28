package br.com.fmchagas.api.pais_estado.pais;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/paises")
public class PaisController {
	
	private PaisRepository paisRepository;

	public PaisController(PaisRepository paisRepository) {
		this.paisRepository = paisRepository;
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<PaisResponse> cadastra(@RequestBody @Valid PaisRequest request){
		final Pais pais = request.toModel();
		
		paisRepository.save(pais);
		
		final PaisResponse response = new PaisResponse(pais);
		
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
}
