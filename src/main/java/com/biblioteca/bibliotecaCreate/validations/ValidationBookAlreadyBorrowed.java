package com.biblioteca.bibliotecaCreate.validations;

import com.biblioteca.bibliotecaCreate.Repository.LoanRepository;
import com.biblioteca.bibliotecaCreate.dto.loanDTO.DataBooksLoan;
import com.biblioteca.bibliotecaCreate.infra.exception.ValidException;
import org.springframework.stereotype.Component;

@Component
public class ValidationBookAlreadyBorrowed implements ValidationLoan {

    private final LoanRepository repository;

    public ValidationBookAlreadyBorrowed(LoanRepository repository){
        this.repository = repository;
    }
    public void validation(DataBooksLoan data){
        if (data.idBook() == null){
            return;
        }

        boolean BookAlreadyBorrowed = repository.existsBookIdAndDate(data.idBook(), data.date());

        if (BookAlreadyBorrowed){
            throw new ValidException("Book Already Borrowed in the moment");
        }
    }
}