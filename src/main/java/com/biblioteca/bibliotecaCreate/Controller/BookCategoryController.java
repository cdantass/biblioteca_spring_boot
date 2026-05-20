package com.biblioteca.bibliotecaCreate.Controller;

import com.biblioteca.bibliotecaCreate.Entity.book.BookCategory;
import com.biblioteca.bibliotecaCreate.Repository.BookCategoryRepository;
import com.biblioteca.bibliotecaCreate.dto.bookCategoryDTO.BookCategoryDetail;
import com.biblioteca.bibliotecaCreate.dto.bookCategoryDTO.BookCategoryRegister;
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
@RequestMapping("/booksCategory")
public class BookCategoryController {

    @Autowired
    private BookCategoryRepository repository;

    @PostMapping
    @Transactional

    public ResponseEntity<BookCategoryDetail> register(@RequestBody @Valid BookCategoryRegister bookCategoryRegister, UriComponentsBuilder uriComponentsBuilder){
        var bookCategory = repository.save(new BookCategory(bookCategoryRegister));

        var uri = uriComponentsBuilder.path("/bookCategory/{id}")
                .buildAndExpand(bookCategory.getId()).toUri();

        return ResponseEntity.created(uri)
                .body(new BookCategoryDetail(bookCategory));
    }
}
