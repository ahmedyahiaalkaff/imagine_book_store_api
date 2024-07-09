package com.imagine.imagine_book_store.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.imagine.imagine_book_store.entity.Book;
import com.imagine.imagine_book_store.exception.BookNotFoundException;
import com.imagine.imagine_book_store.repository.BookRepo;

@RestController
public class BookController {

  private final BookRepo repository;

  public BookController(BookRepo repository){
    this.repository = repository;
  }

  @GetMapping({"/books", "/books/"})
  public List<Book> getAllBooks(){
    return repository.findAll();
  }

  @GetMapping("/books/{id}")
  public Book getBookById(@PathVariable Long id){
    return repository.findById(id).orElseThrow(()->new BookNotFoundException(id));
  }


}
