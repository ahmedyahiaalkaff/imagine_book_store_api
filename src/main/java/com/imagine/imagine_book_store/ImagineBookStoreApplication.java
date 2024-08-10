package com.imagine.imagine_book_store;

import org.hibernate.cfg.Environment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("file:${user.dir}/.env")
public class ImagineBookStoreApplication{

  private static final Logger logger = LoggerFactory.getLogger(ImagineBookStoreApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(ImagineBookStoreApplication.class, args);
	}

}
