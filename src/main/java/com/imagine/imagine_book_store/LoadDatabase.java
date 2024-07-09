package com.imagine.imagine_book_store;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.imagine.imagine_book_store.entity.Book;
import com.imagine.imagine_book_store.repository.BookRepo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Configuration
public class LoadDatabase {

  private static final Logger logger = LoggerFactory.getLogger(LoadDatabase.class);

  @Bean
  CommandLineRunner initDatabase(BookRepo repository) {
    return args -> {
      logger.info("PreLoading " + repository.save(new Book("Moby-Dick", "Herman Melville", " Adventure fiction", 100)));
      logger.info("PreLoading "
          + repository.save(new Book("The Pirates! in an Adventure with Whaling", "Gideon Defoe", "Humor", 80)));
    };
  }
}
