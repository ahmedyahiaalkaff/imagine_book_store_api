package com.imagine.imagine_book_store.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.imagine.imagine_book_store.entity.ShoppingCart;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {

}
