package com.biblioteca.bibliotecaCreate.dto.bookCategoryDTO;

import jakarta.validation.constraints.NotBlank;

public record BookCategoryRegister(@NotBlank String NameCategory) {
}
