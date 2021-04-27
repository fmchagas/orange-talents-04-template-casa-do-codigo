package br.com.fmchagas.api.livro;

import java.math.BigDecimal;

public class LivroResponse {
	
	private String titulo;
	private String isbn;
	private Integer numeroPagina;
	private BigDecimal valor;
	
	public LivroResponse(Livro livro) {
		titulo = livro.getTitulo();
		isbn = livro.getIsbn();
		numeroPagina = livro.getNumeroPagina();
		valor = livro.getValor();
	}

	public String getTitulo() {
		return titulo;
	}
	
	public String getIsbn() {
		return isbn;
	}
	
	public Integer getNumeroPagina() {
		return numeroPagina;
	}
	
	public BigDecimal getValor() {
		return valor;
	}
}
