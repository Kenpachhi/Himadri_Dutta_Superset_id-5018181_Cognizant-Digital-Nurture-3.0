package com.bookstoreapi.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<Map<String, String>> handleResourceNotFoundException(ResourceNotFoundException ex) {
		return buildResponseEntity(Map.of("error", ex.getMessage()), HttpStatus.NOT_FOUND, "ResourceNotFound");
	}

	@ExceptionHandler(InvalidInputException.class)
	public ResponseEntity<Map<String, String>> handleInvalidInputException(InvalidInputException ex) {
		return buildResponseEntity(Map.of("error", ex.getMessage()), HttpStatus.BAD_REQUEST, "InvalidInput");
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Map<String, String>> handleGlobalException(Exception ex) {
		return buildResponseEntity(Map.of("error", "An unexpected error occurred: " + ex.getMessage()),
				HttpStatus.INTERNAL_SERVER_ERROR, "GlobalException");
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.put(fieldName, errorMessage);
		});
		return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
	}

	private ResponseEntity<Map<String, String>> buildResponseEntity(Map<String, String> errorDetails, HttpStatus status,
			String headerValue) {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Custom-Header", headerValue);
		return new ResponseEntity<>(errorDetails, headers, status);
	}
}
