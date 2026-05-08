package com.biblioteca.bibliotecaCreate.dto.clientDTO;

import com.biblioteca.bibliotecaCreate.Entity.client.Client;

public record DataListClient(Long id, String name, String mobileNumber) {
    public DataListClient(Client client){
        this(client.getId(), client.getName(), client.getMobileNumber());
    }
}
