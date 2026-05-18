package com.biblioteca.bibliotecaCreate.validations;

import com.biblioteca.bibliotecaCreate.dto.loanDTO.DataBooksLoan;
import com.biblioteca.bibliotecaCreate.infra.exception.ValidException;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;

@Component
public class ValidationOpeningHours implements ValidationLoan{

    public void validation(DataBooksLoan data){
        var LoanDate = data.date();
        var sunday = LoanDate.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var openLibrary = LoanDate.getHour() < 6;
        var closeLibrary = LoanDate.getHour() > 22;
        if (sunday || openLibrary || closeLibrary){
            throw new ValidException("Library is closed");
        }
    }

}
