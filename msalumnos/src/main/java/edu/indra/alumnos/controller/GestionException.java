package edu.indra.alumnos.controller;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(basePackages = {"edu.indra.alumnos"}) //Todas las excptions que pasen esta clase se va a enterar
public class GestionException {
	
	//Para cada tipo de Excption que quiera gestionar, necesito un metodo
	
	@ExceptionHandler(EmptyResultDataAccessException.class) //Cuando te llegue este error, llamas a este metodo
	public ResponseEntity<?> errorBorradoNoExiste (EmptyResultDataAccessException e) {
		ResponseEntity<?> responseEntity = null;
		String error = "Error al borrar ALUMNO - " + e.getMessage();
		responseEntity = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
		
		return responseEntity;
	}
	
	@ExceptionHandler(Throwable.class)
	public ResponseEntity<?> errorGenerico (Throwable e) {
		ResponseEntity<?> responseEntity = null;
		String error = "Error genérico - " + e.getMessage();
		responseEntity = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
		
		return responseEntity;
	}

}
