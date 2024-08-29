package com.imagine.imagine_book_store.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.imagine.imagine_book_store.entity.Book;

public interface BookRepo extends JpaRepository<Book, Long> {

  public List<Book> findByTitleContainingAndAuthorContainingAndGenreContainingAllIgnoreCase(String title, String author, String genre);

}
