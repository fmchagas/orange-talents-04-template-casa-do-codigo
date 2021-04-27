package br.com.fmchagas.api.shared.validation;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.util.Assert;

public class ExistsIdValidator implements ConstraintValidator<ExistsId, Object>{
	private String fieldName;
	private Class<?> clazzEntity;
	@PersistenceContext private EntityManager manager;


	@Override
	public void initialize(ExistsId params) {
		fieldName = params.fieldName();
		clazzEntity = params.clazzEntity();
	}
	
	
	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		
		if(value == null) return true;
		
		Query query = manager.createQuery("SELECT 1 FROM " + clazzEntity.getName() +
				 " WHERE " + fieldName + "=:pValue");
		
		query.setParameter("pValue", value);
		
		int contador = (int) query.getResultStream().count();
		
		Assert.state(contador <= 1, "encontramos mais de um " + clazzEntity.getName() + " com o atributo " + fieldName + " = " + value + ", quantidade encontrada: " + contador);		 
		
		return contador >= 1;
	}

}