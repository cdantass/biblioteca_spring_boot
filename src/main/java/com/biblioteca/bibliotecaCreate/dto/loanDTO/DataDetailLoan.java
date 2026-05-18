package com.biblioteca.bibliotecaCreate.dto.loanDTO;

import com.biblioteca.bibliotecaCreate.Entity.loan.Loan;

import java.time.LocalDateTime;

public record DataDetailLoan(Long id, Long idClient, Long idBook, LocalDateTime date) {
    public DataDetailLoan(Loan saveLoan){
        this(saveLoan.getId(), saveLoan.getClient().getId(), saveLoan.getBook().getId(),saveLoan.getDate());
    }
}
