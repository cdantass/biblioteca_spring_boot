package com.biblioteca.bibliotecaCreate.dto.bookCategoryDTO;

import com.biblioteca.bibliotecaCreate.Entity.book.BookCategory;

public record BookCategoryList(Long id, String nameCategory) {

    public BookCategoryList(BookCategory bookCategory){
        this(bookCategory.getId(), bookCategory.getNameCategory());
    }
}
