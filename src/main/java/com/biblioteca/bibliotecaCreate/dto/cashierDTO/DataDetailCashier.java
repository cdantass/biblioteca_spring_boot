package com.biblioteca.bibliotecaCreate.dto.cashierDTO;

import com.biblioteca.bibliotecaCreate.Entity.adress.Address;
import com.biblioteca.bibliotecaCreate.Entity.cashier.Cashier;

public record DataDetailCashier(Long id, String name, String email, String mobileNumber, Address address) {

    public DataDetailCashier(Cashier cashier){
        this(cashier.getId(), cashier.getName(), cashier.getEmail(), cashier.getMobileNumber(), cashier.getAddress());
    }
}
