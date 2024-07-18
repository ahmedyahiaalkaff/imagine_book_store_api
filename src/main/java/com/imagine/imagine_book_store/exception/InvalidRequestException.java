package com.imagine.imagine_book_store.exception;

public class InvalidRequestException extends RuntimeException{

  public InvalidRequestException(){
    super();
  }

  public InvalidRequestException(String message){
    super(message);
  }

}
