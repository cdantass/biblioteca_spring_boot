package com.biblioteca.bibliotecaCreate.dto.bookDTO;

import com.biblioteca.bibliotecaCreate.Entity.book.Book;

public record DataDetailBook(
        Long id,
        String title,
        String author,
        String isbn,
        Integer totalCopies,
        Integer availableCopies,
        Boolean active,
        Long categoryId,
        String categoryName
) {
    public DataDetailBook(Book book) {
        this(
                book.getId(),
                book.getTitle(),
                book.getAuthor(),
                book.getIsbn(),
                book.getTotalCopies(),
                book.getAvailableCopies(),
                book.getActive(),
                book.getBookCategory() != null ? book.getBookCategory().getId() : null,
                book.getBookCategory() != null ? book.getBookCategory().getNameCategory() : null
        );
    }
}