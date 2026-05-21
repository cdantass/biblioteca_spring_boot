package com.biblioteca.bibliotecaCreate.Entity.book;

import com.biblioteca.bibliotecaCreate.dto.bookCategoryDTO.BookCategoryRegister;
import com.biblioteca.bibliotecaCreate.dto.bookCategoryDTO.BookCategoryUpdate;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "books_categories")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class BookCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name_category", nullable = false)
    private String nameCategory;

    @Column(nullable = false)
    private boolean active;

    public BookCategory(@Valid BookCategoryRegister bookCategoryRegister) {
        this.nameCategory = bookCategoryRegister.nameCategory();
        this.active = true;
    }

    public void updateBookCategory(@Valid BookCategoryUpdate update) {
        if (update.NameCategory() != null) {
            this.nameCategory = update.NameCategory();
        }
    }

    public void delete() {
        this.active = false;
    }
}