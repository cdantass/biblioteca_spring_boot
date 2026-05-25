package com.biblioteca.bibliotecaCreate.dto.clientDTO;


import com.biblioteca.bibliotecaCreate.Entity.client.Client;
import jakarta.validation.constraints.NotNull;

public record DataUpdateClient(@NotNull Long id, String name, String email, String mobileNumber) {
    public DataUpdateClient(Client client) {
        this(client.getId(), client.getName(), client.getEmail(), client.getMobileNumber());
    }
}
