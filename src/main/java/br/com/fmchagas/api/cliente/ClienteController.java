package br.com.fmchagas.api.cliente;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fmchagas.api.pais_estado.estado.EstadoRepository;
import br.com.fmchagas.api.pais_estado.pais.PaisRepository;

@RestController
@RequestMapping("/api/v1/clientes")
public class ClienteController {
	
	private PaisRepository paisRepository;
	private EstadoRepository estadoRepository;
	private ClienteRepository clienteRepository;
	private PaisTemEstadoValidator paisTemEstadoValidator;
	private EstadoPertenceAoPaisValidator estadoPertenceAoPaisValidator;
	
	@Autowired
	public ClienteController(PaisRepository paisRepository, EstadoRepository estadoRepository,
			ClienteRepository clienteRepository, PaisTemEstadoValidator paisTemEstadoValidator,
			EstadoPertenceAoPaisValidator estadoPertenceAoPaisValidator) {
		this.paisRepository = paisRepository;
		this.estadoRepository = estadoRepository;
		this.clienteRepository = clienteRepository;
		this.paisTemEstadoValidator = paisTemEstadoValidator;
		this.estadoPertenceAoPaisValidator = estadoPertenceAoPaisValidator;
	}
	
	
	@InitBinder
	public void init(WebDataBinder binder) {
		binder.addValidators(paisTemEstadoValidator, estadoPertenceAoPaisValidator);
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<String> adiciona(@Valid @RequestBody ClienteRequest request) {
		Cliente cliente = request.toModel(paisRepository, estadoRepository);
		
		clienteRepository.save(cliente);
		
		return ResponseEntity.ok(cliente.getId().toString());
	}
}
