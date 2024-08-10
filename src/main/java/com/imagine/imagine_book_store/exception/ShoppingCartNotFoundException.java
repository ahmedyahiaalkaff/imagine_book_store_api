package com.imagine.imagine_book_store.exception;

public class ShoppingCartNotFoundException extends RuntimeException{

  public ShoppingCartNotFoundException(Long id){
    super("Could not find Shopping Cart with id: "+id);
  }

}
