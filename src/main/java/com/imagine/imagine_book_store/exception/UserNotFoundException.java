package com.imagine.imagine_book_store.exception;

public class UserNotFoundException extends RuntimeException{
  public UserNotFoundException(){
    super("could not find user");
  }

  public UserNotFoundException(String message){
    super(message);
  }
}
