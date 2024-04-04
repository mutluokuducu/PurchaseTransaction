package com.wex.exception;

import org.springframework.core.convert.ConversionException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(ConversionException.class)
  public ResponseEntity<String> handleConversionException(ConversionException ex) {
    return ResponseEntity.badRequest().body(ex.getMessage());
  }
}
