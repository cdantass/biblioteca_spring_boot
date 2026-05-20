package com.biblioteca.bibliotecaCreate.Entity.book;

import com.biblioteca.bibliotecaCreate.dto.bookDTO.DataRegisterBook;
import com.biblioteca.bibliotecaCreate.dto.loanDTO.DataBooksLoan;
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

    private String title;
    private String author;
    private String isbn;
    private Integer totalCopies;
    private Integer availableCopies;
    private Boolean active;

    @ManyToOne
    @JoinColumn(name = "category_id")
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

    public Book(@Valid DataRegisterBook registerBook) {
    }

    public void updateBook(@Valid DataBooksLoan data) {
        if (data.idBook() != null) {
            this.id = data.idBook();
        }
        if (data.bookCategory() != null) {
            this.bookCategory = data.bookCategory();
        }
    }
}