package com.biblioteca.bibliotecaCreate.dto.loanDTO;

import com.biblioteca.bibliotecaCreate.Entity.book.BookCategory;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DataBooksLoan(Long idBook, @NotNull Long idClient, @NotNull @Future LocalDateTime date, BookCategory bookCategory) {
}
