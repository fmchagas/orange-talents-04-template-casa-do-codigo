package br.com.fmchagas.api.cliente;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import br.com.fmchagas.api.pais_estado.estado.Estado;
import br.com.fmchagas.api.pais_estado.estado.EstadoRepository;
import br.com.fmchagas.api.pais_estado.pais.Pais;
import br.com.fmchagas.api.pais_estado.pais.PaisRepository;

@Component
public class EstadoPertenceAoPaisValidator implements Validator {
	
	private EstadoRepository estadoRepository;
	private PaisRepository paisRepository;

	@Autowired
	public EstadoPertenceAoPaisValidator(EstadoRepository estadoRepository, PaisRepository paisRepository) {
		this.estadoRepository = estadoRepository;
		this.paisRepository = paisRepository;
	}
	
	@Override
	public boolean supports(Class<?> clazz) {
		return ClienteRequest.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		if (errors.hasErrors()) return;

		ClienteRequest request = (ClienteRequest) target;
		
		if(request.estadoIdPresente()) {
			Optional<Estado> possivelEstado = estadoRepository.findById(request.getEstadoId());
			Optional<Pais> possivelPais = paisRepository.findById(request.getPaisId());
			
			Assert.state(possivelEstado.isPresent(), "O estado deve existir no banco de dados, tentamos consultar por -> estadoId: " + request.getEstadoId());
			Assert.state(possivelPais.isPresent(), "O país deve existir no banco de dados, tentamos consultar por -> paisId: " + request.getPaisId());
			
			Estado estado = possivelEstado.get();
			Pais pais = possivelPais.get();
			
			if (estado.naoPertenceAoPais(pais))
				errors.reject("estadoId", null, "o estado não pertencer ao país selecionado!");
		}
	}

}
