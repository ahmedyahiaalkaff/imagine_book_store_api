package com.imagine.imagine_book_store.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.imagine.imagine_book_store.entity.Order;
import com.imagine.imagine_book_store.entity.User;



public interface OrderRepository extends JpaRepository<Order, Long> {
  public List<Order> findByUser(User user);
  public Optional<Order> findByIdAndUser(Long id, User user);
}
