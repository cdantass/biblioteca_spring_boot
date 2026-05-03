package com.biblioteca.bibliotecaCreate.dto.cashierDTO;

import com.biblioteca.bibliotecaCreate.dto.addressDTO.DataAddress;
import jakarta.validation.constraints.NotNull;

public record DataUpdateCashier(@NotNull Long id, String name, String email, String mobileNumber, DataAddress address) {
}
