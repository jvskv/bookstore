package com.example.yasinkirjakauppa;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.yasinkirjakauppa.domain.Book;
import com.example.yasinkirjakauppa.domain.BookRepository;
import com.example.yasinkirjakauppa.domain.Category;
import com.example.yasinkirjakauppa.domain.CategoryRepository;
import com.example.yasinkirjakauppa.domain.User;
import com.example.yasinkirjakauppa.domain.UserRepository;

@SpringBootApplication
public class YasinkirjakauppaApplication {
	private static final Logger log = LoggerFactory.getLogger(YasinkirjakauppaApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(YasinkirjakauppaApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(BookRepository brepository, CategoryRepository crepository,
			UserRepository urepository) {
		return (args) -> {
			log.info("save a couple of books");
			crepository.save(new Category("testCategory1"));
			crepository.save(new Category("testCategory2"));
			crepository.save(new Category("testCategory3"));

			brepository.save(new Book("exampleTitle", "exampleAuthor", 2020, "123-ABC", 20.00,
					crepository.findByName("testCategory1").get(0)));
			brepository.save(new Book("exampleTitle2", "exampleAuthor2", 2022, "1234-ABCE", 10.00,
					crepository.findByName("testCategory2").get(0)));

			User user1 = new User("user", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "USER");
			User user2 = new User("admin", "$2a$10$0MMwY.IQqpsVc1jC8u7IJ.2rT8b0Cd3b3sfIBGV2zfgnPGtT4r0.C", "ADMIN");
			urepository.save(user1);
			urepository.save(user2);
			
			log.info("fetch all books");
			for (Book book : brepository.findAll()) {
				log.info(book.toString());
			}
		};

	}
}
