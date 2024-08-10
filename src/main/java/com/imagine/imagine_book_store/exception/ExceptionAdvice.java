package com.imagine.imagine_book_store.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;

@RestControllerAdvice
public class ExceptionAdvice {

  @ExceptionHandler(BookNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  Map<String, String> handleBookNotFound(BookNotFoundException ex) {
    return Map.of("error", ex.getMessage());
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  Map<String, Map<String, String>> handleArgumentNotValid(MethodArgumentNotValidException ex) {
    Map<String, String> errors = new HashMap<>();
    ex.getBindingResult().getAllErrors().forEach((error) -> {
      String fieldName = ((FieldError) error).getField();
      String errorMessage = error.getDefaultMessage();
      errors.put(fieldName, errorMessage);
    });
    return Map.of("error", errors);
  }

  @ExceptionHandler(NoResourceFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  Map<String, String> handleResourceNotFoundException(NoResourceFoundException ex) {
    return Map.of("error", "resource not found");
  }

  @ExceptionHandler(ShoppingCartNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  Map<String, String> handleShoppingCartNotFoundException(ShoppingCartNotFoundException ex) {
    return Map.of("error", ex.getLocalizedMessage());
  }

  @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  Map<String, String> handleRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException ex) {
    return Map.of("error", ex.getMethod() + " method not supported");
  }

  @ExceptionHandler(InvalidRequestException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  Map<String, String> handleInvalidRequestException(InvalidRequestException ex) {
    return Map.of("error", ex.getMessage());
  }

  @ExceptionHandler(BadCredentialsException.class)
  @ResponseStatus(HttpStatus.UNAUTHORIZED)
  Map<String, String> handleBadCredentialsException(BadCredentialsException ex) {
    return Map.of("error", ex.getMessage());
  }

  @ExceptionHandler(AccessDeniedException.class)
  @ResponseStatus(HttpStatus.FORBIDDEN)
  Map<String, String> handleAccessDeniedException(AccessDeniedException ex) {
    return Map.of("error", ex.getMessage());
  }

  @ExceptionHandler({JWTVerificationException.class, TokenExpiredException.class})
  @ResponseStatus(HttpStatus.FORBIDDEN)
  Map<String, String> handleJWTVerificationException(JWTVerificationException ex) {
    return Map.of("error", ex.getMessage());
  }

  @ExceptionHandler({Exception.class, NullPointerException.class})
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  Map<String, String> handleException(Throwable ex) {
    ex.printStackTrace();
    return Map.of("error", "internal server error");
  }



}
