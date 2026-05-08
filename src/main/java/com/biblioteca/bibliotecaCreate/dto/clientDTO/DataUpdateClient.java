package com.biblioteca.bibliotecaCreate.dto.clientDTO;

import jakarta.validation.constraints.NotNull;

public record DataUpdateClient(@NotNull Long id, String name, String email, String mobileNumber) {
}
