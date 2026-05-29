package com.biblioteca.bibliotecaCreate.dto.cashierDTO;

import com.biblioteca.bibliotecaCreate.Entity.cashier.Cashier;

public record DataUpdateCashier(
        String name,
        String email,
        String mobileNumber
) {
    public DataUpdateCashier(Cashier cashier) {
        this(cashier.getName(), cashier.getEmail(), cashier.getMobileNumber());
    }
}