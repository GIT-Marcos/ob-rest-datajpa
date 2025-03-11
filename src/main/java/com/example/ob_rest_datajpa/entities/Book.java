package com.example.ob_rest_datajpa.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @Column
    private String author;

    @Column
    private String edition;

    @Column
    private LocalDate editionDate;

    @Column
    private Double price;

    @Column
    private Boolean online;

    public Book(){}

    public Book(String title, String author, String edition,
                LocalDate editionDate, Double price, Boolean online) {
        this.title = title;
        this.author = author;
        this.edition = edition;
        this.editionDate = editionDate;
        this.price = price;
        this.online = online;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    public LocalDate getEditionDate() {
        return editionDate;
    }

    public void setEditionDate(LocalDate editionDate) {
        this.editionDate = editionDate;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Boolean getOnline() {
        return online;
    }

    public void setOnline(Boolean online) {
        this.online = online;
    }

    @Override
    public String toString() {
        return "Libro{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", edition='" + edition + '\'' +
                ", editionDate=" + editionDate +
                ", price=" + price +
                ", online=" + online +
                '}';
    }

    @PrePersist
    public void nulleaId(){
        this.id = null;
    }
}
