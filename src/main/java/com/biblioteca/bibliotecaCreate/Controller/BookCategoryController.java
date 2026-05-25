package com.biblioteca.bibliotecaCreate.Controller;

import com.biblioteca.bibliotecaCreate.Service.bookCategoryService.BookCategoryService;
import com.biblioteca.bibliotecaCreate.dto.bookCategoryDTO.BookCategoryDetail;
import com.biblioteca.bibliotecaCreate.dto.bookCategoryDTO.BookCategoryList;
import com.biblioteca.bibliotecaCreate.dto.bookCategoryDTO.BookCategoryRegister;
import com.biblioteca.bibliotecaCreate.dto.bookCategoryDTO.BookCategoryUpdate;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/booksCategory")
public class BookCategoryController {

    @Autowired
    private BookCategoryService bookCategoryService;

    @PostMapping
    @Transactional

    public ResponseEntity<BookCategoryDetail> register(@RequestBody @Valid BookCategoryRegister bookCategoryRegister, UriComponentsBuilder uriComponentsBuilder){
        var bookCategoryDetail = bookCategoryService.register(bookCategoryRegister);

        var uri = uriComponentsBuilder.path("/bookCategory/{id}")
                .buildAndExpand(bookCategoryDetail.id()).toUri();

        return ResponseEntity.created(uri)
                .body(bookCategoryDetail);
    }

    @GetMapping
    public ResponseEntity<Page<BookCategoryList>> list(Pageable pageable){
        return ResponseEntity.ok(bookCategoryService.list(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookCategoryDetail> detail(@PathVariable Long id){
        return ResponseEntity.ok(bookCategoryService.detail(id));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<BookCategoryUpdate> update(@PathVariable Long id, @RequestBody @Valid BookCategoryUpdate bookCategoryUpdate){
        return ResponseEntity.ok(bookCategoryService.update(id, bookCategoryUpdate));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> delete(@PathVariable Long id){
        bookCategoryService.delete(id);
        return ResponseEntity.noContent().build();
    }
}