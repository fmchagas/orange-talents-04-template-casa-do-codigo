package br.com.fmchagas.api.shared.handler;

import java.util.ArrayList;
import java.util.List;

public class ErroDeValidacaoResponse {
	private List<String> messagensDeErrosGlobais = new ArrayList<>();
	private List<ErroDeCampoResponse> errosDeCampo = new ArrayList<>();
	
	public void addError(String messagemErro) {
		messagensDeErrosGlobais.add(messagemErro);
	}
	
	public void addFieldError(String field, String message) {
		ErroDeCampoResponse fieldError = new ErroDeCampoResponse(field, message);
		errosDeCampo.add(fieldError);
	}
	
	public List<String> getMessagensDeErrosGlobais() {
		return messagensDeErrosGlobais;
	}
	
	public List<ErroDeCampoResponse> getErrosDeCampo() {
		return errosDeCampo;
	}
}