package com.infy.todo.utility;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.infy.todo.exception.TodoException;
import com.infy.todo.model.ErrorDTO;

@RestControllerAdvice
public class ExceptionHandlerAspect {
	
	@Autowired
	Environment environment;
	
	//Advice method that handles exceptions for specific handle type
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorDTO> handleException(TodoException e) {
		ErrorDTO errorDTO = new ErrorDTO();
		
		errorDTO.setErrorCode(HttpStatus.BAD_REQUEST.value());
		errorDTO.setErrorMessage(environment.getProperty(e.getMessage()));
		return new ResponseEntity<ErrorDTO>(errorDTO, HttpStatus.BAD_REQUEST);
		
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorDTO> handleException(Exception e) {
		ErrorDTO errorDTO = new ErrorDTO();
		
		errorDTO.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		errorDTO.setErrorMessage(environment.getProperty("GENERAL_EXCEPTION"));
		return new ResponseEntity<ErrorDTO>(errorDTO, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	

}
