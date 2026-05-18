package com.biblioteca.bibliotecaCreate.validations;

import com.biblioteca.bibliotecaCreate.Repository.BookRepository;
import com.biblioteca.bibliotecaCreate.dto.loanDTO.DataBooksLoan;
import com.biblioteca.bibliotecaCreate.infra.exception.ValidException;
import org.springframework.stereotype.Component;

@Component
public class ValidationBookActive implements ValidationLoan{

    private final BookRepository repository;

    public validation(DataBooksLoan data, BookRepository repository){
        this.repository = repository;
        if (data.idBook() == null){
            return;
        }

        boolean bookActive = repository.existsByIdAndActive(data.idBook());

        if (!bookActive){
            throw new ValidException("Book not found/active or not exist");
        }
    }
}
