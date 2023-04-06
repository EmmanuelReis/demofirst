package com.santander.demo.app.exception;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.santander.demo.app.exception.ResponseErrorDto.ErrorMessage;

@RestControllerAdvice
public class CustomExceptionHandler {
    
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(value = MethodArgumentNotValidException.class)
	public ResponseErrorDto handlerExceptions(MethodArgumentNotValidException exception) {
		ResponseErrorDto responseError = new ResponseErrorDto();
		List<ErrorMessage> listMessages = new ArrayList<>();
		
		for(FieldError objError : exception.getFieldErrors()) {
			listMessages.add(responseError.new ErrorMessage(objError.getDefaultMessage() + ". Field: " + objError.getField()));
		}
		responseError.setErrors(listMessages);

		return responseError;
	}

	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	@ExceptionHandler(value = NotFoundException.class)
	public ResponseErrorDto handlerExceptions(NotFoundException exception) {
		ResponseErrorDto responseError = new ResponseErrorDto();

		List<ErrorMessage> listMessages = new ArrayList<>();
		listMessages.add(responseError.new ErrorMessage("Recurso não encontrado"));

		responseError.setErrors(listMessages);

		return responseError;
	}
	
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(value = IllegalArgumentException.class)
	public ResponseErrorDto handlerExceptions(IllegalArgumentException exception) {
		ResponseErrorDto responseError = new ResponseErrorDto();

		List<ErrorMessage> listMessages = new ArrayList<>();
		listMessages.add(responseError.new ErrorMessage(exception.getMessage()));

		responseError.setErrors(listMessages);

		return responseError;
	}

	// @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
	// @ExceptionHandler(value = Exception.class)
	// public ResponseErrorDto handlerExceptions(Exception exception) {
	// 	ResponseErrorDto responseError = new ResponseErrorDto();

	// 	List<ErrorMessage> listMessages = new ArrayList<>();
	// 	listMessages.add(responseError.new ErrorMessage("Erro no sistema"));

	// 	responseError.setErrors(listMessages);

	// 	return responseError;
	// }

	// HttpMediaTypeNotSupportedException
}
