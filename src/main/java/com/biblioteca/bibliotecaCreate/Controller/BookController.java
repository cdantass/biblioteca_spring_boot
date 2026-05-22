package com.biblioteca.bibliotecaCreate.Controller;

import com.biblioteca.bibliotecaCreate.Service.bookService.BookService;
import com.biblioteca.bibliotecaCreate.dto.bookDTO.DataDetailBook;
import com.biblioteca.bibliotecaCreate.dto.bookDTO.DataListBook;
import com.biblioteca.bibliotecaCreate.dto.bookDTO.DataRegisterBook;
import com.biblioteca.bibliotecaCreate.dto.bookDTO.DataUpdateBook;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping
    public ResponseEntity<DataDetailBook> register(
            @RequestBody @Valid DataRegisterBook registerBook,
            UriComponentsBuilder uriComponentsBuilder) {

        var dataDetail = bookService.register(registerBook);

        var uri = uriComponentsBuilder
                .path("/books/{id}")
                .buildAndExpand(dataDetail.id())
                .toUri();

        return ResponseEntity.created(uri).body(dataDetail);
    }

    @GetMapping
    public ResponseEntity<Page<DataListBook>> list(Pageable pageable) {
        return ResponseEntity.ok(bookService.list(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DataDetailBook> detail(@PathVariable Long id) {
        return ResponseEntity.ok(bookService.detail(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DataUpdateBook> update(
            @PathVariable Long id,
            @RequestBody @Valid DataUpdateBook dataUpdateBook) {
        return ResponseEntity.ok(bookService.update(id, dataUpdateBook));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        bookService.delete(id);
        return ResponseEntity.noContent().build();
    }
}