package br.com.fmchagas.api.pais_estado.estado;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstadoRepository extends CrudRepository<Estado, Long>{

	@Query(value="SELECT count(1) FROM estado WHERE pais_id = :paisId", nativeQuery = true)
	Long contaEstadoPorPais(Long paisId);
}
