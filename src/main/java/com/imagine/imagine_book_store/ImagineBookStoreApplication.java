package com.imagine.imagine_book_store;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("file:${user.dir}/.env")
public class ImagineBookStoreApplication{

	public static void main(String[] args) {
		SpringApplication.run(ImagineBookStoreApplication.class, args);
	}

}
