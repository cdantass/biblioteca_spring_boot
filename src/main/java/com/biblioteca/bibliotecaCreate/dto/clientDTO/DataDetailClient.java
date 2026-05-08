package com.biblioteca.bibliotecaCreate.dto.clientDTO;

import com.biblioteca.bibliotecaCreate.Entity.client.Client;

public record DataDetailClient(Long id, String name, String email, String mobileNumber) {

    public DataDetailClient(Client client){
        this(client.getId(), client.getName(), client.getEmail(), client.getMobileNumber());
    }
}
