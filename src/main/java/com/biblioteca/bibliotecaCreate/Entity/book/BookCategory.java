package com.biblioteca.bibliotecaCreate.Entity.book;

import com.biblioteca.bibliotecaCreate.dto.bookDTO.DataRegisterBook;
import com.biblioteca.bibliotecaCreate.dto.bookDTO.DataRegisterCategory;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "booksCategories")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class BookCategory {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nameCategory;

    private boolean active;

    public BookCategory(DataRegisterCategory dataRegisterCategory){
        this.nameCategory = dataRegisterCategory.nameCategory();
        this.active = true;
    }
}
