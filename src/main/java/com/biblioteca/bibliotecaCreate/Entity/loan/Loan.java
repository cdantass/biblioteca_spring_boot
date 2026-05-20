package com.biblioteca.bibliotecaCreate.Entity.loan;

import com.biblioteca.bibliotecaCreate.Entity.book.Book;
import com.biblioteca.bibliotecaCreate.Entity.client.Client;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity(name = "loan")
@Table(name = "Loans")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    private Book book;

    private LocalDateTime date;

    private boolean returned;

    private boolean active;

    public Loan(Long id, Client client, Book book, LocalDateTime date){
        this.id = id;
        this.client = client;
        this.book = book;
        this.date = date;
        this.active = true;
        this.returned = false;
    }

    public void changeBook(Book newBook){
        if (!this.active){
            throw new IllegalStateException("Inactive loan cannot be changed");
        }
        this.book = newBook;
    }
}
