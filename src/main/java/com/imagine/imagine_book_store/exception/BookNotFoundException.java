package com.imagine.imagine_book_store.exception;

public class BookNotFoundException extends RuntimeException {
  public BookNotFoundException(Long id) {
    super("Could not find Book with id: " + id);
  }
}
