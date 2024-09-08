package com.imagine.imagine_book_store;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.imagine.imagine_book_store.auth.AuthService;
import com.imagine.imagine_book_store.dtos.UserSignupDTO;
import com.imagine.imagine_book_store.entity.Book;
import com.imagine.imagine_book_store.entity.ShoppingCart;
import com.imagine.imagine_book_store.entity.User;
import com.imagine.imagine_book_store.enums.UserRole;
import com.imagine.imagine_book_store.repository.BookRepo;
import com.imagine.imagine_book_store.repository.ShoppingCartRepository;
import com.imagine.imagine_book_store.repository.UserRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Configuration
@Profile("dev")
public class LoadDatabase {

  private static final Logger logger = LoggerFactory.getLogger(LoadDatabase.class);

  @Autowired
  AuthService authService;

  @Bean
  CommandLineRunner initDatabase(BookRepo bookRepository, UserRepository userRepository, ShoppingCartRepository shoppingCartRepository) {
    return args -> {
      logger.info("PreLoading " + bookRepository.save(new Book("Moby-Dick", "Herman Melville", " Adventure fiction", 100, 1)));
      logger.info("PreLoading "
          + bookRepository.save(new Book("The Pirates! in an Adventure with Whaling", "Gideon Defoe", "Humor", 80, 1)));

      User user = authService.signup(new UserSignupDTO("user", "user@example.com", "mylongpassword"));
      logger.info("creating user " + user);
      User adminUser = authService.signup(new UserSignupDTO("admin", "admin@example.com", "mylongpassword"), UserRole.ADMIN);
      logger.info("creating admin user " + adminUser);
    };
  }
}
