package com.biblioteca.bibliotecaCreate.dto.bookCategoryDTO;

import com.biblioteca.bibliotecaCreate.Entity.book.BookCategory;

public record BookCategoryDetail(Long id, String nameCategory, Boolean active) {
    public BookCategoryDetail(BookCategory bookCategory){
        this(bookCategory.getId(), bookCategory.getNameCategory(), bookCategory.isActive());
    }
}
