package br.com.fmchagas.api.categoria;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/v1/categorias")
public class CategoriaController {
	
	private CategoriaRepository categoriaRepository;
	
	public CategoriaController(CategoriaRepository categoriaRepository) {
		this.categoriaRepository = categoriaRepository;
	}
	
	@ApiOperation(value = "Insere uma Categoria", notes = "Insere uma Categoria")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK")
    })
	@PostMapping
	@Transactional
	public ResponseEntity<?> adiciona(@Valid @RequestBody CategoriaRequest request){
		final Categoria categoriaSalva = categoriaRepository.save(request.toModel());
		
		final CategoriaResponse response = new CategoriaResponse(categoriaSalva);
		
		return ResponseEntity.ok(response);
	}
}
