package com.example.ob_rest_datajpa;

import com.example.ob_rest_datajpa.entities.Book;
import com.example.ob_rest_datajpa.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Optional;

@SpringBootApplication
public class ObRestDatajpaApplication {

	public static void main(String[] args) {
		ApplicationContext ap = SpringApplication.run(ObRestDatajpaApplication.class, args);
		System.out.println("ashex");
		BookRepository bookRep = ap.getBean(BookRepository.class);

		System.out.println(bookRep.count());

		HashSet<Book> set = new HashSet<>();
		Book b = new Book("asdaw", "asdawdasd",
				"fgfgfgfg", LocalDate.now(), 33d,true);
		Book b2 = new Book("22222", "asdawdasd",
				"fgfgfgfg", LocalDate.now(), 33d,true);
		set.add(b);
		set.add(b2);
		bookRep.saveAll(set);

		System.out.println(bookRep.findAll());

	}

}
