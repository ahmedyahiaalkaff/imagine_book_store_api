package com.imagine.imagine_book_store.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.imagine.imagine_book_store.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{

  public User findByEmail(String email);
}
