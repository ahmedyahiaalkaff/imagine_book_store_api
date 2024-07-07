package com.imagine.imagine_book_store;

import lombok.Data;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Data
@Entity
public class Book {
  private @Id @GeneratedValue long id;
  private String title;
  private String author;
  private String genre;
  private double price;

  public Book(){
    
  }

  public Book(String title, String author, String genre, double price){
    this.setTitle(title);
    this.setAuthor(author);
    this.setGenre(genre);
    this.setPrice(price);
  }

}
