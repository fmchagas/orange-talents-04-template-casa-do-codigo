package br.com.fmchagas.api.pais_estado.estado;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fmchagas.api.pais_estado.pais.Pais;
import br.com.fmchagas.api.pais_estado.pais.PaisRepository;

@RestController
@RequestMapping("/api/v1/estados")
public class EstadoController {
	private EstadoRepository estadoRepository;
	private PaisRepository paisRepository;

	public EstadoController(EstadoRepository estadoRepository, PaisRepository paisRepository) {
		this.estadoRepository = estadoRepository;
		this.paisRepository = paisRepository;
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<EstadoResponse> cadastra(@RequestBody @Valid EstadoRequest request){
		final Pais pais = paisRepository.findById((Long)request.getPaisId()).get();
		
		final Estado estado = request.toModel(pais);
		
		estadoRepository.save(estado);
		
		final EstadoResponse response = new EstadoResponse(estado);
		
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
}
