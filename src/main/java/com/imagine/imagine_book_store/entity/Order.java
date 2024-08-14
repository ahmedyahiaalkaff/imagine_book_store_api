package com.imagine.imagine_book_store.entity;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "order_table")
public class Order {

  @Id
  @GeneratedValue
  private Long id;

  private LocalDateTime date;

  @JsonIgnore
  @ManyToOne
  private User user;

  @OneToMany
  private List<Book> books;
}
