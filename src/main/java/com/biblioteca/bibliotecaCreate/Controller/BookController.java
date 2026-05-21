package com.biblioteca.bibliotecaCreate.Controller;

import com.biblioteca.bibliotecaCreate.Entity.book.Book;
import com.biblioteca.bibliotecaCreate.Repository.BookCategoryRepository;
import com.biblioteca.bibliotecaCreate.Repository.BookRepository;
import com.biblioteca.bibliotecaCreate.dto.bookDTO.DataDetailBook;
import com.biblioteca.bibliotecaCreate.dto.bookDTO.DataListBook;
import com.biblioteca.bibliotecaCreate.dto.bookDTO.DataRegisterBook;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookRepository repository;

    @Autowired
    private BookCategoryRepository bookCategoryRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<DataDetailBook> register(
            @RequestBody @Valid DataRegisterBook registerBook,
            UriComponentsBuilder uriComponentsBuilder) {

        var category = bookCategoryRepository.
                findById(registerBook.categoryId())
                .orElseThrow(() -> new EntityNotFoundException("Category not found"));

        var book = repository.save(new Book(registerBook, category));

        var uri = uriComponentsBuilder
                .path("/books/{id}")
                .buildAndExpand(book.getId())
                .toUri();

        return ResponseEntity.created(uri)
                .body(new DataDetailBook(book));
    }

    @GetMapping
    public ResponseEntity<Page<DataListBook>> list(Pageable pageable) {
        var page = repository.findAll(pageable)
                .map(DataListBook::new);
        return ResponseEntity.ok(page);
    }
}
