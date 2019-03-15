package com.scaffolding.spring.error;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler {

	@Inject
	private MessageSource messageSource;

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorDetail> handleValidationError(MethodArgumentNotValidException manve,
			HttpServletRequest request) {

		ErrorDetail errorDetail = new ErrorDetail();
		errorDetail.setTimeStamp(new Date().getTime());
		errorDetail.setStatus(HttpStatus.BAD_REQUEST.value());
		errorDetail.setTitle(messageSource.getMessage("api.validation.error.rest.exception.handler.validation.failed",
				null, Locale.getDefault()));
		errorDetail.setDetail(messageSource.getMessage(
				"api.validation.error.rest.exception.handler.input.validation.failed", null, Locale.getDefault()));
		errorDetail.setDeveloperMessage(manve.getClass().getName());

		List<FieldError> fieldErrors = manve.getBindingResult().getFieldErrors();

		fieldErrors.forEach(fe -> {
			List<ValidationError> validationErrorList = errorDetail.getErrors().get(fe.getField());
			if (validationErrorList == null) {
				validationErrorList = new ArrayList<>();
				errorDetail.getErrors().put(fe.getField(), validationErrorList);
			}
			ValidationError validationError = new ValidationError();
			validationError.setCode(fe.getCode());
			validationError.setMessage(fe.getDefaultMessage());
			validationErrorList.add(validationError);
		});

		return new ResponseEntity<>(errorDetail, null, HttpStatus.BAD_REQUEST);
	}

}
