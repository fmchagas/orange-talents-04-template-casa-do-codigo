package br.com.fmchagas.api.livro;

import java.util.List;
import java.util.stream.Collectors;

public class LivroNomeEIdResposne {
	
	private Long id;
	private String nome;

	public LivroNomeEIdResposne(Livro livro) {
		id = livro.getId();
		nome = livro.getTitulo();
	}
	
	public static List<LivroNomeEIdResposne> converter(List<Livro> livros){
		return livros.stream().map(LivroNomeEIdResposne::new).collect(Collectors.toList());
	}
	
	public Long getId() {
		return id;
	}
	
	public String getNome() {
		return nome;
	}
}
