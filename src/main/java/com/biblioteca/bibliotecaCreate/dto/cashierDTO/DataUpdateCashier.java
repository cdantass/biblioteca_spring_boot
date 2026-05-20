package com.biblioteca.bibliotecaCreate.dto.cashierDTO;

import com.biblioteca.bibliotecaCreate.dto.addressDTO.DataAddress;
import jakarta.validation.constraints.NotNull;

public record DataUpdateCashier(String name, String email, String mobileNumber, DataAddress address) {
}
