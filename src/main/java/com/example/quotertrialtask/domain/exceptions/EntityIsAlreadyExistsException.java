package com.example.quotertrialtask.domain.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class EntityIsAlreadyExistsException extends RuntimeException {
  public EntityIsAlreadyExistsException(String message) {
    super(message);
  }
}
