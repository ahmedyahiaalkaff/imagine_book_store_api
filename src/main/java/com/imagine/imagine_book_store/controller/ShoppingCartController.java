package com.imagine.imagine_book_store.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.imagine.imagine_book_store.entity.Book;
import com.imagine.imagine_book_store.entity.ShoppingCart;
import com.imagine.imagine_book_store.entity.User;
import com.imagine.imagine_book_store.exception.BookNotFoundException;
import com.imagine.imagine_book_store.exception.ShoppingCartNotFoundException;
import com.imagine.imagine_book_store.repository.BookRepo;
import com.imagine.imagine_book_store.repository.ShoppingCartRepository;
import com.imagine.imagine_book_store.repository.UserRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/shopping-cart")
public class ShoppingCartController {

  @Autowired
  private ShoppingCartRepository shoppingCartRepository;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private BookRepo bookRepository;

  @GetMapping("")
  public ResponseEntity<List<Book>> getShoppingCartBooks(@AuthenticationPrincipal User user) {
    var id = user.getId();
    ShoppingCart shoppingCart = shoppingCartRepository.findById(id)
        .orElseThrow(() -> new ShoppingCartNotFoundException(id));
    return ResponseEntity.ok().body(shoppingCart.getBooks());
  }

  @PutMapping("/{bookId}")
  public ResponseEntity<?> addBookToShoppingCart(@AuthenticationPrincipal User user, @PathVariable Long bookId) {
    ShoppingCart shoppingCart = shoppingCartRepository.findById(user.getId())
    .orElseThrow(() -> new ShoppingCartNotFoundException(user.getId()));
    Book book = bookRepository.findById(bookId).orElseThrow(() -> new BookNotFoundException(bookId));
    if(!shoppingCart.getBooks().contains(book)){
      shoppingCart.addBook(book);
      shoppingCartRepository.save(shoppingCart);
    }
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }

  @DeleteMapping("/{bookId}")
  public ResponseEntity<?> removeBookToShoppingCart(@AuthenticationPrincipal User user, @PathVariable Long bookId) {
    ShoppingCart shoppingCart = shoppingCartRepository.findById(user.getId())
    .orElseThrow(() -> new ShoppingCartNotFoundException(user.getId()));
    Book book = bookRepository.findById(bookId).orElseThrow(() -> new BookNotFoundException(bookId));
    if(shoppingCart.getBooks().contains(book)){
      shoppingCart.removeBook(book);
      shoppingCartRepository.save(shoppingCart);
    }
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }

}
