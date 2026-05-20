package com.biblioteca.bibliotecaCreate.Controller;

import com.biblioteca.bibliotecaCreate.Repository.BookRepository;
import com.biblioteca.bibliotecaCreate.Repository.LoanRepository;
import com.biblioteca.bibliotecaCreate.Service.loanService.LoanService;
import com.biblioteca.bibliotecaCreate.dto.loanDTO.DataBooksLoan;
import com.biblioteca.bibliotecaCreate.dto.loanDTO.DataUpdateLoanBook;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/loans")
public class LoanController {

    private final LoanRepository loanRepository;

    private final BookRepository bookRepository;

    private final LoanService loanService;

    public LoanController(LoanRepository loanRepository, LoanService loanService, BookRepository bookRepository){
        this.loanRepository = loanRepository;
        this.loanService = loanService;
        this.bookRepository = bookRepository;
    }

    @PostMapping
    @Transactional
    public ResponseEntity <DataBooksLoan> agendarloan(@RequestBody @Valid DataBooksLoan data){
        var detail = loanService.loan(data);
        return ResponseEntity.status(201).body(detail);
    }

    @GetMapping
    public List <DataBooksLoan> getAllLoans(){
        return loanService.getAllLoans();
    }

    @PatchMapping
    @Transactional
    public ResponseEntity updateNewBook(@PathVariable Long id, @RequestBody @Valid DataUpdateLoanBook data){
        var updateLoan = loanService.updateBook(id, data);
        return ResponseEntity.ok(updateLoan);
    }
}
