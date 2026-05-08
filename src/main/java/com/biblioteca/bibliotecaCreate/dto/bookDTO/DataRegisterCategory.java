package com.biblioteca.bibliotecaCreate.dto.bookDTO;

import jakarta.validation.constraints.NotBlank;

public record DataRegisterCategory(@NotBlank(message = "A category is required") String nameCategory) {
}
