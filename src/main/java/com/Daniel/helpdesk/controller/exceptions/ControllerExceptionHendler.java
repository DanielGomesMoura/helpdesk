package com.Daniel.helpdesk.controller.exceptions;

import com.Daniel.helpdesk.service.exception.DataIntegrityViolationException;
import com.Daniel.helpdesk.service.exception.ObjectNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHendler{

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<StandardError> objectNotFoundException(ObjectNotFoundException ex,
    		HttpServletRequest request){

        StandardError error = new StandardError(System.currentTimeMillis(), HttpStatus.NOT_FOUND.value(),
        		"Objeto não encontrado", ex.getMessage(), request.getRequestURI());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }
    
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<StandardError> dataIntegrityViolationException(DataIntegrityViolationException ex,
    		HttpServletRequest request){

        StandardError error = new StandardError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(),
        		"Violação de Dados", ex.getMessage(), request.getRequestURI());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
    
   
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandardError> dataIntegrityViolationException(MethodArgumentNotValidException ex,
    		HttpServletRequest request){

        ValidationError errors = new ValidationError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(),
        		"Validation Error", "Erro na Validação dos Campos" , request.getRequestURI());

        for (FieldError x : ex.getBindingResult().getFieldErrors()) {
			errors.addError(x.getField(),x.getDefaultMessage());
		}
        
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }
    
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<StandardError> constraintViolationException(ConstraintViolationException ex,
    		HttpServletRequest request){

    	ValidationError errors = new ValidationError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(),
    			"Validation Error","Erro na Validação dos Campos" , request.getRequestURI());

    	if(ex.getMessage().contains("CPF")) {
    		errors.addError("CPF Invalido","O CPF consta como incorreto" );	
    	}
    	if(ex.getMessage().contains("email")) {
    		errors.addError("EMAIL Invalido","O EMAIL consta como incorreto" );	
    	}
    	 
    	
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }
}
