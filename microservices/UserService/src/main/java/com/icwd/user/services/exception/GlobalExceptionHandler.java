package com.icwd.user.services.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.icwd.user.services.payload.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<ApiResponse> handlerResourceNotFoundException(ResourceNotFoundException ex){
    String message = ex.getMessage();
    ApiResponse response = ApiResponse.builder()
    .message(message)
    .success(false)
    .status(HttpStatus.NOT_FOUND)
    .build();

    return new ResponseEntity<ApiResponse>(response, HttpStatus.NOT_FOUND);
  }
}
