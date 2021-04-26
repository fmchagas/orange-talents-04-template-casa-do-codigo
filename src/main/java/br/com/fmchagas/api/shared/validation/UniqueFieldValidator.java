package br.com.fmchagas.api.shared.validation;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.util.Assert;

public class UniqueFieldValidator implements ConstraintValidator<UniqueField, Object>{
	@PersistenceContext private EntityManager em;
	private Class<?> clazzEntity;
	private String fieldName;
	
	@Override
	public void initialize(UniqueField constraintAnnotation) {
		clazzEntity = constraintAnnotation.clazzEntity();
		fieldName = constraintAnnotation.fieldName();
	}

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		Query query = em.createQuery("SELECT 1 FROM " + clazzEntity.getName() 
						+ " WHERE " + fieldName + "= :pValue");
		
		query.setParameter("pValue", value);		
		
		int contador = (int) query.getResultStream().count();
		
		Assert.state(contador <= 1, "encontramos mais de um " + clazzEntity.getName() + " com o atributo " + fieldName + " = " + value + ", quantidade encontrada: " + contador);
		
		return contador < 1 ;
	}

}
