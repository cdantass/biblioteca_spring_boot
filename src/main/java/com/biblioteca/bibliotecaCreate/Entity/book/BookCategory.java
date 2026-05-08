package com.biblioteca.bibliotecaCreate.Entity.book;

import com.biblioteca.bibliotecaCreate.dto.bookDTO.DataRegisterBook;
import com.biblioteca.bibliotecaCreate.dto.bookDTO.DataRegisterCategory;
import jakarta.persistence.*;

@Entity
@Table(name = "booksCategories")
public class BookCategory {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nameCategory;

    private boolean active;

    public BookCategory(){}

    public BookCategory(DataRegisterCategory dataRegisterCategory){
        this.nameCategory = dataRegisterCategory.nameCategory();
        this.active = true;
    }
}
