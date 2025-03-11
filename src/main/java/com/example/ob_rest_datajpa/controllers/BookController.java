package com.example.ob_rest_datajpa.controllers;

import com.example.ob_rest_datajpa.entities.Book;
import com.example.ob_rest_datajpa.repositories.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class BookController {

    /**
     * prueba de perfiles con archivo config.
     */
    @Value("${app.message}")
    private String perfil;

    private final BookRepository bookRep;

    private final Logger log = LoggerFactory.getLogger(BookController.class);

    public BookController(BookRepository bookRep) {
        this.bookRep = bookRep;
    }


    //TODOS LOS LIBROS
    @GetMapping("/api/allBooks")
    public ResponseEntity<List<Book>> findAll() {
        System.out.println(perfil);

        List<Book> list =bookRep.findAll();
        if (list.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(list);
    }

    //TRAE UN SOLO LIBRO
    @GetMapping("/api/book/{bookId}")
    public ResponseEntity<Book> findById(@PathVariable Long bookId) {
        Optional<Book> bookOpt = bookRep.findById(bookId);
        ///OPCIÓN 1 MAL HECHA PERO ANDA
        /*
        return bookOpt.orElse(null);*/
        ///OPCIÓN 2 BIEN HECHA
        /*
        if (bookOpt.isPresent()){
            return ResponseEntity.ok(bookOpt.get());
        } else {
            return ResponseEntity.notFound().build();
        }*/
        ///OPCIÓN 3 BIEN HECHA Y PRO
        return bookOpt.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    //CREA UN LIBRO NUEVO
    @PostMapping("/api/addBook")
    public ResponseEntity<Book> addBook(@RequestBody Book book) {
        if (book.getId() != null) {
            log.warn("TRYING TO CREATE BOOK WITH AN EXISTING ID");
            return ResponseEntity.badRequest().build();
        } else {
            Book result = bookRep.save(book);
            return ResponseEntity.ok(result);
        }
    }

    //ACTUALIZA LIBRO
    @PutMapping("/api/editBook")
    public ResponseEntity<Book> editBook(@RequestBody Book book) {
        if (book.getId() == null) {
            log.warn("TRYING TO EDIT A BOOK WITH A NULL ID");
            return ResponseEntity.badRequest().build();
        }
        if (!bookRep.existsById(book.getId())) {
            log.warn("TRYING TO EDIT A NON EXISTENT BOOK IN DB");
            return ResponseEntity.notFound().build();
        }
        Book result = bookRep.save(book);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/api/deleteBook/{bookId}")
    public ResponseEntity<Book> deleteBook(@PathVariable Long bookId) {
        if (bookId == null) {
            log.warn("TRYING TO DELETE A BOOK WITH A NULL ID");
            return ResponseEntity.badRequest().build();
        }
        if (!bookRep.existsById(bookId)) {
            log.warn("TRYING TO DELETE A NON EXISTENT BOOK");
            return ResponseEntity.notFound().build();
        }
        bookRep.deleteById(bookId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/api/deleteAllBooks")
    public ResponseEntity<Book> deleteAllBooks() {
        if (bookRep.count()==0) {
            log.warn("NO BOOKS IN DB");
            return ResponseEntity.notFound().build();
        }
        bookRep.deleteAll();
        return ResponseEntity.noContent().build();
    }
}
