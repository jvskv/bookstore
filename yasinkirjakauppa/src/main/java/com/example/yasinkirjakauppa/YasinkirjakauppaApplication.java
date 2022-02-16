package com.example.yasinkirjakauppa;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.yasinkirjakauppa.domain.Book;
import com.example.yasinkirjakauppa.domain.BookRepository;

@SpringBootApplication
public class YasinkirjakauppaApplication {
	private static final Logger log = LoggerFactory.getLogger(YasinkirjakauppaApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(YasinkirjakauppaApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(BookRepository repository) {
		return (args) -> {
			log.info("save a couple of books");
			repository.save(new Book("exampleTitle", "exampleAuthor", 2020, "123-ABC", 20.00));
			repository.save(new Book("exampleTitle2", "exampleAuthor2", 2022, "1234-ABCE", 10.00));

			log.info("fetch all books");
			for (Book book : repository.findAll()) {
				log.info(book.toString());
			}
		};

	}
}
