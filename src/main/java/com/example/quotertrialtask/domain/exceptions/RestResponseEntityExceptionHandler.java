package com.example.quotertrialtask.domain.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(value = {ResourceNotFoundException.class})
  @ResponseStatus(HttpStatus.NOT_FOUND)
  protected ResponseEntity<Object> handleNotFound(ResourceNotFoundException exception, WebRequest request) {
    String message = exception.getMessage();
    return handleExceptionInternal(exception, message, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
  }

  @ExceptionHandler(value = {EntityIsAlreadyExistsException.class})
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  protected ResponseEntity<Object> handleAlreadyExists(EntityIsAlreadyExistsException exception, WebRequest request) {
    String message = exception.getMessage();
    return handleExceptionInternal(exception, message, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
  }

}
