package com.biblioteca.bibliotecaCreate.Entity.book;

import com.biblioteca.bibliotecaCreate.dto.bookDTO.DataRegisterBook;
import jakarta.persistence.*;

@Entity
@Table(name = "books")
public class Book {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    public Book(){}

    public Book(DataRegisterBook dataRegisterBook, BookCategory bookCategory){
        this.title = dataRegisterBook.title();
        this.author = dataRegisterBook.author();
        this.isbn = dataRegisterBook.isbn();
        this.totalCopies = dataRegisterBook.totalCopies();
        this.active = true;
        this.bookCategory = bookCategory;
    }
}
