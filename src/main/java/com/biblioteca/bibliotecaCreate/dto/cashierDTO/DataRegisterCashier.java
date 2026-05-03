package com.biblioteca.bibliotecaCreate.dto.cashierDTO;

import com.biblioteca.bibliotecaCreate.dto.addressDTO.DataAddress;

public record DataRegisterCashier(String name, String email, String mobileNumber, DataAddress address) {
}
