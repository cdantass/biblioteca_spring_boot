package com.biblioteca.bibliotecaCreate.dto.bookDTO;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DataRegisterBook(

        @NotBlank(message = "Título é obrigatório")
        String title,

        @NotBlank(message = "Autor é obrigatório")
        String author,

        @NotBlank(message = "ISBN é obrigatório")
        String isbn,

        @NotNull(message = "Quantidade de exemplares é obrigatória")
        @Min(value = 1, message = "Deve ter ao menos 1 exemplar")
        Integer totalCopies,

        @NotNull(message = "Categoria é obrigatória")
        Long categoryId

) {}
