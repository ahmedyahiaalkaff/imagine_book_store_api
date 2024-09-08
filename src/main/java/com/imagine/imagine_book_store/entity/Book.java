package com.imagine.imagine_book_store.entity;

import lombok.Data;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

@Data
@Entity
public class Book {
  private @Id @GeneratedValue long id;

  @NotEmpty
  private String title;

  @NotEmpty
  private String author;

  @NotEmpty
  private String genre;

  @Positive
  private double price;

  @PositiveOrZero
  private double stock;

  public Book(){
  }

  public Book(String title, String author, String genre, double price, double stock){
    this.setTitle(title);
    this.setAuthor(author);
    this.setGenre(genre);
    this.setPrice(price);
    this.setStock(stock);
  }

  public void incrementStock(int increment){
    if(increment <= 0){
      throw new IllegalArgumentException("increment must be positive");
    }
    this.stock += increment;
  }

  public void decrementStock(int decrement){
    if(decrement <= this.stock){
      throw new IllegalArgumentException("decrement must be less than or equal to stock");
    }
    this.stock -= decrement;
  }

}
