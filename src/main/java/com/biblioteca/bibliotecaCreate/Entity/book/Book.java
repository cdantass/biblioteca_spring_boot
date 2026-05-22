package com.biblioteca.bibliotecaCreate.Entity.book;

import com.biblioteca.bibliotecaCreate.dto.bookDTO.DataRegisterBook;
import com.biblioteca.bibliotecaCreate.dto.bookDTO.DataUpdateBook;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "books")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String author;

    @Column(nullable = false, unique = true)
    private String isbn;

    @Column(nullable = false)
    private Integer totalCopies;

    @Column(nullable = false)
    private Integer availableCopies;

    @Column(nullable = false)
    private Boolean active;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private BookCategory bookCategory;

    public Book(DataRegisterBook dataRegisterBook, BookCategory bookCategory) {
        this.title = dataRegisterBook.title();
        this.author = dataRegisterBook.author();
        this.isbn = dataRegisterBook.isbn();
        this.totalCopies = dataRegisterBook.totalCopies();
        this.availableCopies = dataRegisterBook.totalCopies();
        this.active = true;
        this.bookCategory = bookCategory;
    }

    public void updateBookEntity(@Valid DataUpdateBook dataUpdateBook) {
        if (dataUpdateBook.title() != null) {
            this.title = dataUpdateBook.title();
        }
        if (dataUpdateBook.author() != null) {
            this.author = dataUpdateBook.author();
        }
        if (dataUpdateBook.isbn() != null) {
            this.isbn = dataUpdateBook.isbn();
        }
        if (dataUpdateBook.totalCopies() != null) {
            this.totalCopies = dataUpdateBook.totalCopies();
        }
        if (dataUpdateBook.availableCopies() != null) {
            this.availableCopies = dataUpdateBook.availableCopies();
        }
        if (dataUpdateBook.active() != null) {
            this.active = dataUpdateBook.active();
        }
    }

    public void delete() {
        this.active = false;
    }
}