package com.example.demo.exception;

import java.sql.SQLException;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
	@Override
	   protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
	       String error = "Malformed JSON request";
	       return buildResponseEntity(new ApiError(HttpStatus.BAD_REQUEST, error, ex));
	   }

	   private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
	       return new ResponseEntity<>(apiError, apiError.getStatus());
	   }
	   
	   @ExceptionHandler(NoSuchProductExistException.class)
	   protected ResponseEntity<Object> handleNoSuchProductExistException(
			   NoSuchProductExistException ex) {
	       ApiError apiError = new ApiError(HttpStatus.NOT_FOUND);
	       apiError.setMessage(ex.getMessage());
	       return buildResponseEntity(apiError);
	   }
	   
	   @ExceptionHandler(EmptyResultDataAccessException.class)
	   protected ResponseEntity<Object> handleEmptyResultDataAccessException(
			   EmptyResultDataAccessException ex) {
		   ApiError apiError = new ApiError(HttpStatus.NOT_FOUND);
	       apiError.setMessage(ex.getMessage());
	       return buildResponseEntity(apiError);
	   }
	   
	   @ExceptionHandler(SQLException.class)
	   protected ResponseEntity<Object> handleSQLException(
			   SQLException ex) {
		   ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST);
	       apiError.setMessage(ex.getMessage());
	       return buildResponseEntity(apiError);
	   }
	   
	   @ExceptionHandler(InternalServerException.class)
	   protected ResponseEntity<Object> handleInternalServerException(
			   InternalServerException ex) {
		   ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR);
	       apiError.setMessage(ex.getMessage());
	       return buildResponseEntity(apiError);
	   }
}
