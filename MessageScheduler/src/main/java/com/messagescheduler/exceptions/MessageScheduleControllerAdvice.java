package com.messagescheduler.exceptions;

import java.text.ParseException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class MessageScheduleControllerAdvice {
	// constants
	private static final String ERRORS = "errors";
	private static final String STATUS = "status";

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Object> handleException(MethodArgumentNotValidException ex) {
		Map<String, Object> body = new LinkedHashMap<>();
		body.put(STATUS, HttpStatus.BAD_REQUEST);
		// Get all validation errors
		List<String> errors = ex.getBindingResult().getFieldErrors().stream().map(x -> x.getDefaultMessage())
				.collect(Collectors.toList());
		body.put(ERRORS, errors);
		return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(ParseException.class)
	public ResponseEntity<Object> handleParseException(ParseException ex) {
		Map<String, Object> body = new LinkedHashMap<>();
		body.put(STATUS, HttpStatus.BAD_REQUEST);
		body.put(ERRORS, ex.getMessage());
		return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<Object> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
		Map<String, Object> body = new LinkedHashMap<>();
		body.put(STATUS, HttpStatus.BAD_REQUEST);
		body.put(ERRORS, ex.getMessage());
		return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> handleException(Exception ex) {
		Map<String, Object> body = new LinkedHashMap<>();
		body.put(STATUS, HttpStatus.INTERNAL_SERVER_ERROR);
		body.put(ERRORS, ex.getMessage());
		return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
	}
}
