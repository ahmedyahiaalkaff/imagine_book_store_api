package com.imagine.imagine_book_store.exception;

public class ShoppingCartEmptyException extends RuntimeException {
  public ShoppingCartEmptyException() {
    super("ShoppingCart is empty");
  }
}
