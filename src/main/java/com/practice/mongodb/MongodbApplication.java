package com.practice.mongodb;

import com.practice.mongodb.entity.Book;
import com.practice.mongodb.repository.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class MongodbApplication implements CommandLineRunner {
	private final BookRepository bookRepository;

	public MongodbApplication(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}



	public static void main(String[] args) {
		SpringApplication.run(MongodbApplication.class, args);
	}

	@Override
	public void run(String... args)  {
		bookRepository.saveAll(List.of(
				new Book(500, "Core Java", 200, "Kathy Sierra", 1065.5),
				new Book(501, "JSP & Servlets", 350, "Kathy Sierra", 1749.0),
				new Book(502, "Spring in Action", 480, "Craig Walls", 940.75),
				new Book(503, "Pro Angular", 260, "Freeman", 1949.25),
				new Book(504, "HTML CSS", 100, "Thomas Powell", 2317.09),
				new Book(505, "Hibernate in Action", 180, "Gavin King", 889.25),
				new Book(506, "Practical MongoDB", 180, "Shakuntala Gupta", 785.0),
				new Book(507, "Pro Spring Boot", 850, "Felipe Gutierrez", 2167.99),
				new Book(508, "Beginning jQuery", 180, "Franklin", 1500.00),
				new Book(509, "Java Design Patterns", 114, "Devendra Singh", 919.99)

		));

		Book book = new Book();
		book.setName("sukumar Book");
		book.setAuthor("sukumar");
		book.setId(1);

		bookRepository.save(book);
		System.out.println("------All records has been saved successfully-------");
	}
}
