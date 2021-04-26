package br.com.fmchagas.api.shared.handler;

public class ErroDeCampoResponse {

	private String campo;
	private String mensagem;
	
	public ErroDeCampoResponse() {}
	
	public ErroDeCampoResponse(String field, String message) {
		this.campo = field;
		this.mensagem = message;
	}

	public String getCampo() {
		return campo;
	}

	public String getMensagem() {
		return mensagem;
	}
}