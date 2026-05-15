package com.biblioteca.bibliotecaCreate.Repository;

import com.biblioteca.bibliotecaCreate.Entity.loan.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface LoanRepository extends JpaRepository<Loan, Long> {
    boolean existsBookIdAndDate(Long idBook, LocalDateTime date);
}
