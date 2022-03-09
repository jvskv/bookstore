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

@SpringBootApplication
public class YasinkirjakauppaApplication {
	private static final Logger log = LoggerFactory.getLogger(YasinkirjakauppaApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(YasinkirjakauppaApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(BookRepository brepository, CategoryRepository crepository) {
		return (args) -> {
			log.info("save a couple of books");
			crepository.save(new Category("testCategory1"));
			crepository.save(new Category("testCategory2"));
			crepository.save(new Category("testCategory3"));

			brepository.save(new Book("exampleTitle", "exampleAuthor", 2020, "123-ABC", 20.00,
					crepository.findByName("testCategory1").get(0)));
			brepository.save(new Book("exampleTitle2", "exampleAuthor2", 2022, "1234-ABCE", 10.00,
					crepository.findByName("testCategory2").get(0)));

			log.info("fetch all books");
			for (Book book : brepository.findAll()) {
				log.info(book.toString());
			}
		};

	}
}
