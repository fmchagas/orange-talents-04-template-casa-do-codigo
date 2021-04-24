package br.com.fmchagas.api.autor;

public class AutorResponse {
	
	private String nome;
	private String email;
	
	
	public AutorResponse(Autor autor) {
		nome = autor.getNome();
		email = autor.getEmail();
	}
	
	public String getNome() {
		return nome;
	}
	
	public String getEmail() {
		return email;
	}
}
