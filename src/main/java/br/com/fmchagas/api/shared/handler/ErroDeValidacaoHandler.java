package br.com.fmchagas.api.shared.handler;

import java.util.List;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErroDeValidacaoHandler {
private MessageSource messageSource;
	
	public ErroDeValidacaoHandler(MessageSource messageSource) {
		this.messageSource = messageSource;
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ErroDeValidacaoResponse handleValidationError(MethodArgumentNotValidException exception) {
		List<ObjectError> globalErros = exception.getBindingResult().getGlobalErrors();
		List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
		
		return buildValidationErros(globalErros, fieldErrors);
	}
	

	private ErroDeValidacaoResponse buildValidationErros(List<ObjectError> globalErros, 
			List<FieldError> fieldErrors) {
		
		ErroDeValidacaoResponse validationErros = new ErroDeValidacaoResponse();
		
		globalErros.forEach(e -> validationErros.addError(getErrorMessage(e)));
		
		fieldErrors.forEach(e -> {
			String errorMessage = getErrorMessage(e);
			validationErros.addFieldError(e.getField(), errorMessage);
		});
		
		return validationErros;
	}

	private String getErrorMessage(ObjectError error) {
		return messageSource.getMessage(error, LocaleContextHolder.getLocale());
	}
}