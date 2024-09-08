package com.imagine.imagine_book_store.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.imagine.imagine_book_store.entity.Book;
import com.imagine.imagine_book_store.exception.BookNotFoundException;
import com.imagine.imagine_book_store.repository.BookRepo;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;

@RestController
@RequestMapping("/book")
public class BookController {

  @Autowired
  private BookRepo repository;

  @GetMapping()
  public List<Book> getAllBooks(@RequestParam(required = false, defaultValue = "") String title,
      @RequestParam(required = false, defaultValue = "") String author,
      @RequestParam(required = false, defaultValue = "") String genre) {
    return repository.findByTitleContainingAndAuthorContainingAndGenreContainingAllIgnoreCase(title, author, genre);
  }

  @GetMapping("/{id}")
  public Book getBookById(@PathVariable Long id) {
    return repository.findById(id).orElseThrow(() -> new BookNotFoundException(id));
  }

  @PostMapping()
  public ResponseEntity<Book> createBook(@RequestBody(required = true) @Valid Book book){
    Book result = repository.save(book);
    return ResponseEntity.status(HttpStatus.CREATED).body(result);
  }

  @PutMapping("/{id}/increment/{increment}")
  public ResponseEntity<Book> incrementStockBy(@PathVariable Long id, @PathVariable @Positive int increment){
    Book book = repository.findById(id).orElseThrow(()-> new BookNotFoundException(id));
    book.incrementStock(increment);
    book = repository.save(book);
    return ResponseEntity.status(HttpStatus.OK).body(book);
  }

  @PutMapping("/{id}/decrement/{decrement}")
  public ResponseEntity<Book> decrementStockBy(@PathVariable Long id, @PathVariable @Positive int decrement){
    Book book = repository.findById(id).orElseThrow(()-> new BookNotFoundException(id));
    book.decrementStock(decrement);
    book = repository.save(book);
    return ResponseEntity.status(HttpStatus.OK).body(book);
  }
}
