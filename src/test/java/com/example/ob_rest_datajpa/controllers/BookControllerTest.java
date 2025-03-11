package com.example.ob_rest_datajpa.controllers;


import com.example.ob_rest_datajpa.entities.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import java.util.List;
import java.util.Objects;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BookControllerTest {

    @LocalServerPort
    private int port;
    private TestRestTemplate testRestTemplate;
    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    @BeforeEach
    void setUp() {
        restTemplateBuilder = restTemplateBuilder.rootUri("http://localhost:"+port);
        testRestTemplate = new TestRestTemplate(restTemplateBuilder);

    }

    @Test
    void findAll() {

    }

    @Test
    void findById() {
        addBook();
        int id = 1;
        ResponseEntity<Book> response = testRestTemplate.getForEntity("/api/book/"+id, Book.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(id, Objects.requireNonNull(response.getBody()).getId());
        System.out.println(response.getBody());
    }

    @Test
    void addBook() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        String json = """
                {
                    "title": "asdaw",
                    "author": "asdawdasd",
                    "edition": "fgfgfgfg",
                    "editionDate": "2025-03-07",
                    "price": 33.0,
                    "online": true
                }
                """;
        HttpEntity<String> request = new HttpEntity<>(json,headers);
        ResponseEntity<Book> response = testRestTemplate.exchange("/api/addBook", HttpMethod.POST, request,Book.class);
        assertEquals(HttpStatus.OK,response.getStatusCode());
        System.out.println(response.getBody());
    }

    @Test
    void editBook() {
    }

    @Test
    void deleteBook() {
    }

    @Test
    void deleteAllBooks() {
    }
}