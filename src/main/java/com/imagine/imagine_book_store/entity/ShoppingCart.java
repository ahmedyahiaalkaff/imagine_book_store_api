package com.imagine.imagine_book_store.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class ShoppingCart {
  @Id
  @GeneratedValue
  private long id;
  private final List<Book> books;

  public ShoppingCart() {
    books = new ArrayList<Book>();
  }

  public void addBook(Book book) {
    this.books.add(book);
  }

  public void removeBook(Book book) {
    this.books.remove(book);
  }
}
