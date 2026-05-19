package com.biblioteca.bibliotecaCreate.dto.loanDTO;

import com.biblioteca.bibliotecaCreate.Entity.book.BookCategory;
import com.biblioteca.bibliotecaCreate.Entity.loan.Loan;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DataBooksLoan(Long idBook, @NotNull Long idClient, @NotNull @Future LocalDateTime date, BookCategory bookCategory) {
    public DataBooksLoan(Loan saveLoan) {
        this(saveLoan.getBook().getId(), saveLoan.getClient().getId(),saveLoan.getDate(), saveLoan.getBook().getBookCategory());
    }
}
