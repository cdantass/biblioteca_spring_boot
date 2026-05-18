package com.biblioteca.bibliotecaCreate.validations;

import com.biblioteca.bibliotecaCreate.Repository.LoanRepository;
import com.biblioteca.bibliotecaCreate.dto.loanDTO.DataBooksLoan;
import com.biblioteca.bibliotecaCreate.infra.exception.ValidException;
import org.springframework.stereotype.Component;

@Component
public class ValidationClientHasMoreThanTwoBooks {

    private final LoanRepository repository;

    public ValidationClientHasMoreThanTwoBooks(LoanRepository repository){
        this.repository = repository;
    }

    public void validation(DataBooksLoan data){
        if (data.idClient() == null){
            return;
        }

        long totalActiveLoans = repository.countByClientIdAndReturnedFalse(data.idClient());

        if (totalActiveLoans > 2){
            throw new ValidException("The client has more than two books");
        }
    }
}
