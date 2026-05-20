package com.biblioteca.bibliotecaCreate.validations;

import com.biblioteca.bibliotecaCreate.Repository.ClientRepository;
import com.biblioteca.bibliotecaCreate.dto.loanDTO.DataBooksLoan;
import com.biblioteca.bibliotecaCreate.infra.exception.ValidException;
import org.springframework.stereotype.Component;

@Component
public class ValidationClientActive implements ValidationLoan{

    private final ClientRepository repository;

    public ValidationClientActive(ClientRepository repository) {
        this.repository = repository;
    }

    public void validation(DataBooksLoan data){
        if (data.idClient() == null){
            return;
        }

        boolean clientActive = repository.existsByIdAndActive(data.idClient(), true);

        if (!clientActive){
            throw new ValidException("Client not active or not exist");
        }
    }
}
