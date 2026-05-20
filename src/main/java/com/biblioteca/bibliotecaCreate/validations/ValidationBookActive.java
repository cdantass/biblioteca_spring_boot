package com.biblioteca.bibliotecaCreate.validations;

import com.biblioteca.bibliotecaCreate.Repository.BookRepository;
import com.biblioteca.bibliotecaCreate.dto.loanDTO.DataBooksLoan;
import com.biblioteca.bibliotecaCreate.infra.exception.ValidException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidationBookActive implements ValidationLoan{

    @Autowired
    private BookRepository repository;

    public void validation(DataBooksLoan data){
        if (data.idBook() == null){
            return;
        }

        boolean bookActive = repository.existsByIdAndActiveTrue(data.idBook());

        if (!bookActive){
            throw new ValidException("Book not found/active or not exist");
        }
    }
}
