package br.com.fmchagas.api.livro;

import java.math.BigDecimal;

public class LivroDetalheResponse {

	private String titulo;
	private String resumo;
	private String sumario;
	private BigDecimal valor;
	private String isbn;
	private Integer numeroPagina;
	private String nomeAutor;
	private String descricaoAutor;
	

	public LivroDetalheResponse(Livro livro) {
		titulo = livro.getTitulo();
		resumo = livro.getResumo();
		sumario = livro.getSumario();
		valor = livro.getValor();
		isbn = livro.getIsbn();
		numeroPagina = livro.getNumeroPagina();
		nomeAutor = livro.getNomeAutor();
		descricaoAutor = livro.getDescricaoAutor();
	}

	public String getTitulo() {
		return titulo;
	}

	public String getResumo() {
		return resumo;
	}
	
	public String getSumario() {
		return sumario;
	}
	
	public BigDecimal getValor() {
		return valor;
	}

	public String getIsbn() {
		return isbn;
	}

	public Integer getNumeroPagina() {
		return numeroPagina;
	}

	public String getNomeAutor() {
		return nomeAutor;
	}

	public String getDescricaoAutor() {
		return descricaoAutor;
	}
}
