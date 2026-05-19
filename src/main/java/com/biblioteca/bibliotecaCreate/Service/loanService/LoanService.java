package com.biblioteca.bibliotecaCreate.Service.loanService;

import com.biblioteca.bibliotecaCreate.Entity.book.Book;
import com.biblioteca.bibliotecaCreate.Entity.loan.Loan;
import com.biblioteca.bibliotecaCreate.Repository.BookRepository;
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
    private final List<ValidationLoan> validationLoans;
    private boolean active;

    public LoanService(
            LoanRepository loanRepository,
            ClientRepository clientRepository,
            BookRepository bookRepository,
            List<ValidationLoan> validationLoans
    ) {
        this.loanRepository = loanRepository;
        this.clientRepository = clientRepository;
        this.bookRepository = bookRepository;
        this.validationLoans = validationLoans;
        this.active = true;
    }

    public DataBooksLoan loan(DataBooksLoan data) {
        var client = clientRepository.findById(data.idClient())
                .orElseThrow(() -> new EntityNotFoundException("Client ID not found"));

        validationLoans.forEach(validationLoan -> validationLoan.validation(data));

        var book = choiceBook(data);
        var loan = new Loan(null, client, book, data.date());
        var saveLoan = loanRepository.save(loan);

        return new DataBooksLoan(saveLoan);
    }

    private Book choiceBook(DataBooksLoan data) {
        if (data.idBook() == null) {
            throw new IllegalArgumentException("Book ID is required");
        }

        return bookRepository.findById(data.idBook())
                .orElseThrow(() -> new EntityNotFoundException("Book not found"));
    }

    public void cancel(Long id) {
        this.active = false;
    }

    public List<DataBooksLoan> getAllLoans() {
        return loanRepository.findAll()
                .stream()
                .map(DataBooksLoan::new)
                .toList();
    }

    public DataBooksLoan getLoanById(Long id) {
        var loan = loanRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Loan not found"));

        return new DataBooksLoan(loan);
    }

    public DataBooksLoan updateBook(Long idLoan, DataUpdateLoanBook data) {
        var loan = loanRepository.findById(idLoan)
                .orElseThrow(() -> new EntityNotFoundException("Loan not found"));

        var newBook = bookRepository.findById(data.idBook())
                .orElseThrow(() -> new EntityNotFoundException("Book not found"));

        loan.changeBook(newBook);

        return new DataBooksLoan(loan);
    }
}
