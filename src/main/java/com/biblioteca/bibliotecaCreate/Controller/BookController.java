package com.biblioteca.bibliotecaCreate.Controller;

import com.biblioteca.bibliotecaCreate.Entity.book.Book;
import com.biblioteca.bibliotecaCreate.Repository.BookRepository;
import com.biblioteca.bibliotecaCreate.dto.bookDTO.DataDetailBook;
import com.biblioteca.bibliotecaCreate.dto.bookDTO.DataRegisterBook;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity<DataDetailBook> register(@RequestBody @Valid DataRegisterBook registerBook, UriComponentsBuilder uriComponentsBuilder){
        var book = repository.save(new Book(registerBook));

        var uri = uriComponentsBuilder.path("/books/{id}")
                .buildAndExpand(book.getId()).toUri();

        return ResponseEntity.created(uri)
                .body(new DataDetailBook(book));
    }
}
