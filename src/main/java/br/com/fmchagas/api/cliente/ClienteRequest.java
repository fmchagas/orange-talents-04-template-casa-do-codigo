package br.com.fmchagas.api.cliente;

import java.util.Optional;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.util.Assert;

import br.com.fmchagas.api.pais_estado.estado.Estado;
import br.com.fmchagas.api.pais_estado.estado.EstadoRepository;
import br.com.fmchagas.api.pais_estado.pais.Pais;
import br.com.fmchagas.api.pais_estado.pais.PaisRepository;
import br.com.fmchagas.api.shared.validation.CpfOuCnpj;
import br.com.fmchagas.api.shared.validation.ExistsId;
import br.com.fmchagas.api.shared.validation.UniqueField;

public class ClienteRequest {
	
	@NotBlank @Email @Size(max = 128)
	@UniqueField(clazzEntity = Cliente.class, fieldName = "email")
	private String email;
	
	@NotBlank @Size(min = 3, max = 32)
	private String nome;
	
	@NotBlank @Size(min = 2, max = 64)
	private String sobrenome;
	
	@NotBlank @CpfOuCnpj
	@UniqueField(clazzEntity = Cliente.class, fieldName = "documento")
	private String documento;
	
	@NotBlank @Size(max = 128)
	private String endereco;
	
	@NotBlank @Size(max = 32)
	private String complemento;
	
	@NotBlank @Size(max = 64)
	private String cidade;
	
	@NotBlank @Size(max = 15)
	private String telefone;
	
	@NotBlank @Size(max = 8)
	private String cep;
	
	@NotNull @ExistsId(clazzEntity = Pais.class, fieldName = "id")
	private Long paisId;
	
	@ExistsId(clazzEntity = Pais.class, fieldName = "id")
	private Long estadoId;

	public ClienteRequest(@NotBlank @Email String email, @NotBlank @Size(min = 2, max = 32) String nome,
			@NotBlank @Size(min = 2, max = 64) String sobrenome, @NotBlank String documento,
			@NotBlank @Size(max = 128) String endereco, @NotBlank @Size(max = 128) String complemento,
			@NotBlank @Size(max = 64) String cidade, @NotBlank @Size(max = 20) String telefone,
			@NotBlank @Size(max = 8) String cep, @NotNull Long paisId, Long estadoId) {
		
		this.email = email.toLowerCase();
		this.nome = nome.trim();
		this.sobrenome = sobrenome.trim();
		this.documento = limpar(documento);
		this.endereco = endereco;
		this.complemento = complemento;
		this.cidade = cidade;
		this.telefone = limpar(telefone);
		this.cep = limpar(cep);
		this.paisId = paisId;
		this.estadoId = estadoId;
	}
	
	
	public Cliente toModel(PaisRepository paisRepository, EstadoRepository estadoRepository) {
		@NotNull Optional<Pais> pais = paisRepository.findById(paisId);
		
		Assert.state(pais.isPresent(), "O país deve existir no banco de dados, tentamos consultar por -> paisId: " + paisId);
		
		Cliente cliente = new Cliente(email, nome, sobrenome, documento, endereco, complemento, cidade, telefone, cep, pais.get());
		
		if(estadoIdPresente()) {
			Optional<Estado> estado = estadoRepository.findById(estadoId);
			
			Assert.state(estado.isPresent(), "O estado deve existir no banco de dados, tentamos consultar por -> estadoId: " + estadoId);
			
			cliente.setEstado(estado.get());
		}
		
		return cliente;
	}
	
	private String limpar(String valor) {
		return valor.replaceAll("[^0-9]+", "");
	}
	
	public Long getPaisId() {
		return paisId;
	}
	
	public Long getEstadoId() {
		return estadoId;
	}
	
	public boolean estadoIdPresente() {
		return estadoId != null;
	}
	
	public boolean estadoIdEstaVazio() {
		return !estadoIdPresente();
	}
}
