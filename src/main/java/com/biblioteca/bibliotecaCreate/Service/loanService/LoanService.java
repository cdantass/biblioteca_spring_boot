package com.biblioteca.bibliotecaCreate.Service.loanService;

import com.biblioteca.bibliotecaCreate.Entity.book.Book;
import com.biblioteca.bibliotecaCreate.Entity.loan.Loan;
import com.biblioteca.bibliotecaCreate.Repository.BookRepository;
import com.biblioteca.bibliotecaCreate.Repository.CashierRepository;
import com.biblioteca.bibliotecaCreate.Repository.ClientRepository;
import com.biblioteca.bibliotecaCreate.Repository.LoanRepository;
import com.biblioteca.bibliotecaCreate.dto.loanDTO.DataBooksLoan;
import com.biblioteca.bibliotecaCreate.dto.loanDTO.DataUpdateLoanBook;
import com.biblioteca.bibliotecaCreate.validations.ValidationLoan;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoanService {

    private final LoanRepository loanRepository;
    private final ClientRepository clientRepository;
    private final BookRepository bookRepository;
    private final CashierRepository cashierRepository;
    private final List<ValidationLoan> validationLoans;

    public LoanService(
            LoanRepository loanRepository,
            ClientRepository clientRepository,
            BookRepository bookRepository,
            CashierRepository cashierRepository,
            List<ValidationLoan> validationLoans
    ) {
        this.loanRepository = loanRepository;
        this.clientRepository = clientRepository;
        this.bookRepository = bookRepository;
        this.cashierRepository = cashierRepository;
        this.validationLoans = validationLoans;
    }

    public DataBooksLoan loan(DataBooksLoan data) {

        var client = clientRepository.findById(data.idClient())
                .orElseThrow(() -> new EntityNotFoundException("Client ID not found"));

        var cashier = cashierRepository.findById(data.idCashier())
                .orElseThrow(() -> new EntityNotFoundException("Cashier not found"));

        var book = choiceBook(data);

        validationLoans.forEach(validationLoan -> validationLoan.validation(data));

        var loan = new Loan(null, client, book, cashier, data.date());

        var savedLoan = loanRepository.save(loan);

        return new DataBooksLoan(savedLoan);
    }

    private Book choiceBook(DataBooksLoan data) {
        if (data.idBook() == null) {
            throw new IllegalArgumentException("Book ID is required");
        }

        return bookRepository.findById(data.idBook())
                .orElseThrow(() -> new EntityNotFoundException("Book not found"));
    }

    public void cancel(Long id) {
        var loan = loanRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Loan not found"));

        loan.cancel();

        loanRepository.save(loan);
    }

    public List<DataBooksLoan> getAllLoans() {
        return loanRepository.findAll()
                .stream()
                .map(DataBooksLoan::new)
                .toList();
    }

    public DataBooksLoan getLoanById(Long id) {
        var loan = loanRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Loan not found"));

        return new DataBooksLoan(loan);
    }

    public DataBooksLoan updateBook(Long idLoan, DataUpdateLoanBook data) {
        var loan = loanRepository.findById(idLoan)
                .orElseThrow(() -> new EntityNotFoundException("Loan not found"));

        var newBook = bookRepository.findById(data.idBook())
                .orElseThrow(() -> new EntityNotFoundException("Book not found"));

        loan.changeBook(newBook);

        loanRepository.save(loan);

        return new DataBooksLoan(loan);
    }
}