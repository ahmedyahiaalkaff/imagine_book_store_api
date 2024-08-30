package com.imagine.imagine_book_store.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.imagine.imagine_book_store.entity.Book;
import com.imagine.imagine_book_store.exception.BookNotFoundException;
import com.imagine.imagine_book_store.repository.BookRepo;

@RestController
@RequestMapping("/book")
public class BookController {

  @Autowired
  private BookRepo repository;

  @GetMapping("")
  public List<Book> getAllBooks(@RequestParam(required = false, defaultValue = "") String title,
      @RequestParam(required = false, defaultValue = "") String author,
      @RequestParam(required = false, defaultValue = "") String genre) {
    return repository.findByTitleContainingAndAuthorContainingAndGenreContainingAllIgnoreCase(title, author, genre);
  }

  @GetMapping("/{id}")
  public Book getBookById(@PathVariable Long id) {
    return repository.findById(id).orElseThrow(() -> new BookNotFoundException(id));
  }
}
