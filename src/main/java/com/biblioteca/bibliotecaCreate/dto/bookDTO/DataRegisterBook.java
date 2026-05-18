package com.biblioteca.bibliotecaCreate.dto.bookDTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DataRegisterBook(
        @NotBlank
        String title,

        @NotBlank
        String author,

        @NotBlank
        String isbn,

        @NotNull
        Integer totalCopies,

        @NotNull
        Long categoryId
) {
}