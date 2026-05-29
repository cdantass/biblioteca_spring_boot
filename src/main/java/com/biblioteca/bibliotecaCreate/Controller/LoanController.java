package com.biblioteca.bibliotecaCreate.Controller;

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

    private final LoanService loanService;

    public LoanController(LoanService loanService){
        this.loanService = loanService;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<DataBooksLoan> createLoan(@RequestBody @Valid DataBooksLoan data){
        var detail = loanService.loan(data);
        return ResponseEntity.status(201).body(detail);
    }

    @GetMapping
    public ResponseEntity<List<DataBooksLoan>> getAllLoans(){
        var loans = loanService.getAllLoans();
        return ResponseEntity.ok(loans);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DataBooksLoan> getLoanById(@PathVariable Long id){
        var loan = loanService.getLoanById(id);
        return ResponseEntity.ok(loan);
    }

    @PatchMapping("/{id}")
    @Transactional
    public ResponseEntity<DataBooksLoan> updateNewBook(
            @PathVariable Long id,
            @RequestBody @Valid DataUpdateLoanBook data
    ){
        var updateLoan = loanService.updateBook(id, data);
        return ResponseEntity.ok(updateLoan);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> cancelLoan(@PathVariable Long id){
        loanService.cancel(id);
        return ResponseEntity.noContent().build();
    }
}