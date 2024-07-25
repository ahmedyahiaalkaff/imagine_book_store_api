package com.imagine.imagine_book_store.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.imagine.imagine_book_store.entity.Book;
import com.imagine.imagine_book_store.entity.ShoppingCart;
import com.imagine.imagine_book_store.exception.ShoppingCartNotFoundException;
import com.imagine.imagine_book_store.repository.ShoppingCartRepository;

@RestController
public class ShoppingCartController {

  @Autowired
  private ShoppingCartRepository shoppingCartRepository;


  @GetMapping("/shopping-cart/{id}")
  public ResponseEntity<List<Book>> getShoppingCartBooks(@RequestParam Long id){
    ShoppingCart shoppingCart = shoppingCartRepository.findById(id).orElseThrow(()->new ShoppingCartNotFoundException(id));
    return ResponseEntity.ok().body(shoppingCart.getBooks());
  }

}
