package com.imagine.imagine_book_store.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
public class ShoppingCart {

  @Id
  @Column(name = "user_id")
  private long id;

  @OneToMany
  private final List<Book> books;

  @OneToOne
  @JoinColumn(name = "user_id")
  private User user;

  public ShoppingCart(){
    this.books = new ArrayList<Book>();
  }

  public void addBook(Book book) {
    this.books.add(book);
  }

  public void removeBook(Book book) {
    this.books.remove(book);
  }
}
