package br.com.fmchagas.api.pais_estado.estado;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.fmchagas.api.pais_estado.pais.Pais;
import br.com.fmchagas.api.shared.validation.ExistsId;
import br.com.fmchagas.api.shared.validation.UniqueField;

public class EstadoRequest {
	@NotBlank @UniqueField(clazzEntity = Estado.class, fieldName = "nome")
	private String nome;
	
	@NotNull @ExistsId(clazzEntity = Pais.class, fieldName = "id")
	private Long paisId;

	public EstadoRequest(@NotBlank String nome, @NotNull Long paisId) {
		this.nome = nome;
		this.paisId = paisId;
	}

	public Estado toModel(Pais pais) {
		return new Estado(nome, pais);
	}
	
	public Long getPaisId() {
		return paisId;
	}
}
