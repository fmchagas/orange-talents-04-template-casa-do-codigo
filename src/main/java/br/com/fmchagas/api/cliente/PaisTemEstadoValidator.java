package br.com.fmchagas.api.cliente;

import javax.validation.constraints.Positive;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import br.com.fmchagas.api.pais_estado.estado.EstadoRepository;

@Component
public class PaisTemEstadoValidator implements Validator{
	
	private EstadoRepository estadoRepository;
	
	@Autowired
	public PaisTemEstadoValidator(EstadoRepository estadoRepository) {
		this.estadoRepository = estadoRepository;
	}
	
	@Override
	public boolean supports(Class<?> clazz) {
		return ClienteRequest.class.isAssignableFrom(clazz);
	}
	
	@Override
	public void validate(Object target, Errors errors) {
		if(errors.hasErrors()) return;
		
		ClienteRequest request = (ClienteRequest) target;
		
		Long contaEstado = estadoRepository.contaEstadoPorPais(request.getPaisId());
		
		if (paisTemEstado(contaEstado) && request.estadoIdEstaVazio() )
			errors.reject("estadoId", null, "o país tem estado. Selecione um!");
	}
	
	private boolean paisTemEstado(@Positive Long contador) {
		return contador > 0;
	}
}
