package com.imagine.imagine_book_store.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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

  @PostMapping("")
  public ResponseEntity<?> createShoppingCart(@AuthenticationPrincipal User user) {
    if(user == null){
      throw new AccessDeniedException("Access Denied");
    }
    if(shoppingCartRepository.findById(user.getId()) != null){
      return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    ShoppingCart shoppingCart = new ShoppingCart();
    user.setShoppingCart(shoppingCart);
    shoppingCart.setUser(userRepository.getReferenceById(user.getId()));
    shoppingCart = shoppingCartRepository.save(new ShoppingCart());
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @PostMapping("/{shoppingCartId}/book/{bookId}")
  public ResponseEntity<?> addBook(@RequestParam Long shoppingCartId, @RequestParam Long bookId) {
    ShoppingCart shoppingCart = shoppingCartRepository.findById(shoppingCartId)
        .orElseThrow(() -> new ShoppingCartNotFoundException(shoppingCartId));
    Book book = bookRepository.findById(bookId).orElseThrow(() -> new BookNotFoundException(bookId));
    shoppingCart.addBook(book);
    shoppingCartRepository.save(shoppingCart);
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

}
