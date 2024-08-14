package com.imagine.imagine_book_store.controller;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.imagine.imagine_book_store.entity.Book;
import com.imagine.imagine_book_store.entity.Order;
import com.imagine.imagine_book_store.entity.ShoppingCart;
import com.imagine.imagine_book_store.entity.User;
import com.imagine.imagine_book_store.exception.OrderNotFoundException;
import com.imagine.imagine_book_store.exception.ShoppingCartEmptyException;
import com.imagine.imagine_book_store.exception.UserNotFoundException;
import com.imagine.imagine_book_store.repository.OrderRepository;
import com.imagine.imagine_book_store.repository.UserRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/order")
public class OrderController {

  @Autowired
  private OrderRepository orderRepository;
  @Autowired
  private UserRepository userRepository;

  @GetMapping("/{id}")
  public Order getOrderById(@AuthenticationPrincipal User user, @PathVariable @Valid Long id) {
    return this.orderRepository.findByIdAndUser(id, user).orElseThrow(() -> new OrderNotFoundException(id));
  }

  @GetMapping("")
  public List<Order> getAllOrders(@AuthenticationPrincipal User user) {
    return this.orderRepository.findByUser(user);
  }

  @PostMapping("")
  public ResponseEntity<Order> checkoutShoppingCart(@AuthenticationPrincipal User user) {
    user = userRepository.findById(user.getId()).orElseThrow(() -> new UserNotFoundException());
    ShoppingCart shoppingCart = user.getShoppingCart();
    if (shoppingCart != null && shoppingCart.getBooks().size() > 0) {
      Order order = new Order();
      order.setBooks(new ArrayList<Book>(shoppingCart.getBooks()));
      order.setUser(user);
      order.setDate(LocalDateTime.now());
      orderRepository.save(order);
      return ResponseEntity.status(HttpStatus.CREATED).body(order);
    }
    throw new ShoppingCartEmptyException();
  }
}
