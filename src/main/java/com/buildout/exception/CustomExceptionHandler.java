package com.buildout.exception;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Custom ResponseEntityExceptionHandler which is used to handle exceptions
 * occur in request execution and respond back with custom
 * {@link ErrorResponse}.
 * 
 * @author Paragraj Kale
 *
 */
@ControllerAdvice
@Controller
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(Exception.class)
	@Nullable
	public ResponseEntity<ErrorResponse> handleProjectException(@NonNull Exception ex, 
			@NonNull WebRequest request) throws Exception {
		ErrorResponse errorResponse = new ErrorResponse(ex.getMessage(), new Date());
		return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(UserNotFoundException.class)
	@Nullable
	public ResponseEntity<ErrorResponse> handleUserNotFoundException(@NonNull Exception ex, 
			@NonNull WebRequest request) throws Exception {
		ErrorResponse errorResponse = new ErrorResponse(ex.getMessage(), new Date());
		return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.BAD_REQUEST);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			@NonNull HttpHeaders headers, 
			@NonNull HttpStatus status, 
			@NonNull WebRequest request) {
		ErrorResponse errorResponse = new ErrorResponse(ex.getMessage(), new Date());
		return new ResponseEntity<>(errorResponse, status);
	}
}
