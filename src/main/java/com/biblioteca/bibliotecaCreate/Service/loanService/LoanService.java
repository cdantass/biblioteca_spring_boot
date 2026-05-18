package com.biblioteca.bibliotecaCreate.Service.loanService;

import com.biblioteca.bibliotecaCreate.Entity.loan.Loan;
import com.biblioteca.bibliotecaCreate.Repository.BookRepository;
import com.biblioteca.bibliotecaCreate.Repository.CashierRepository;
import com.biblioteca.bibliotecaCreate.Repository.ClientRepository;
import com.biblioteca.bibliotecaCreate.Repository.LoanRepository;
import com.biblioteca.bibliotecaCreate.dto.loanDTO.DataBooksLoan;
import com.biblioteca.bibliotecaCreate.validations.ValidationLoan;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoanService {

    private final LoanRepository loanRepository;
    private final ClientRepository clientRepository;
    private final CashierRepository cashierRepository;
    private final BookRepository bookRepository;
    private List<ValidationLoan> validationLoans;
    private boolean active;

    public LoanService(LoanRepository loanRepository, ClientRepository clientRepository, CashierRepository cashierRepository, BookRepository bookRepository) {
        this.loanRepository = loanRepository;
        this.clientRepository = clientRepository;
        this.cashierRepository = cashierRepository;
        this.bookRepository = bookRepository;
        this.validationLoans = validationLoans;
        this.active = true;
    }

    public LoanService Loan(DataBooksLoan data){
        var client = clientRepository.findById(data.idClient())
                .orElseThrow(()-> new EntityNotFoundException("Client ID not found"));

        if (data.idBook() != null && !bookRepository.existsById(data.idBook())){
            throw new EntityNotFoundException("Book ID not found");
        }

        validationLoans.forEach(validationLoan -> validationLoan.validation(data));

        var book = choiceBook(data);
        var loan = new Loan(null, book, client, data.date());
        var saveLoan = loanRepository.save(loan);

        return new DataBooksLoan(saveLoan);
    }
}
