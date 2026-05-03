package com.biblioteca.bibliotecaCreate.dto.cashierDTO;

import com.biblioteca.bibliotecaCreate.Entity.cashier.Cashier;

public record DataListCashier(Long id, String name, String email, String mobileNumber) {

    public DataListCashier(Cashier cashier){
        this(cashier.getId(), cashier.getName(), cashier.getEmail(), cashier.getMobileNumber());
    }
}