package br.com.fmchagas.api.shared.validation;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

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
		
		return contador < 1 ;
	}

}
