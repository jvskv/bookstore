package com.example.yasinkirjakauppa;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.yasinkirjakauppa.domain.Book;
import com.example.yasinkirjakauppa.domain.BookRepository;
import com.example.yasinkirjakauppa.domain.Category;
import com.example.yasinkirjakauppa.domain.CategoryRepository;
import com.example.yasinkirjakauppa.domain.UserRepository;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class BookRepositoryTest {

	@Autowired
	private BookRepository brepository;
	private CategoryRepository crepository;
	private UserRepository urepository;
	
	@Test
	public void findByBookNameShouldReturnAuthor() {
		List<Book> books = brepository.findByTitle("exampleTitle");
		
		assertThat(books).hasSize(1);
		assertThat(books.get(0).getAuthor()).isEqualTo("exampleAuthor");
	}
	
	
	@Test
	public void createNewBook() {
		Book book = new Book("testBook", "testAuthor", 2019, "PRQ-300", 30.0, new Category("Crime"));
		brepository.save(book);
		assertThat(book.getId()).isNotNull();
	}
}
