package com.imagine.imagine_book_store.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.imagine.imagine_book_store.entity.Book;

public interface BookRepo extends JpaRepository<Book, Long> {

}
