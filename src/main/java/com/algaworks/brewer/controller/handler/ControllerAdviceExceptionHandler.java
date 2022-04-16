package com.algaworks.brewer.controller.handler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.algaworks.brewer.service.exception.EstiloCadastradoException;

@ControllerAdvice
public class ControllerAdviceExceptionHandler {

	@ExceptionHandler(EstiloCadastradoException.class)
	public ResponseEntity <String> estiloCadastradoExceptionHandler(EstiloCadastradoException e) {
		return ResponseEntity.badRequest().body(e.getMessage());
	}
	
}
