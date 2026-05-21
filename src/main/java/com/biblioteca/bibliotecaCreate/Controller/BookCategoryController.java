package com.biblioteca.bibliotecaCreate.Controller;

import com.biblioteca.bibliotecaCreate.Entity.book.BookCategory;
import com.biblioteca.bibliotecaCreate.Repository.BookCategoryRepository;
import com.biblioteca.bibliotecaCreate.dto.bookCategoryDTO.BookCategoryDetail;
import com.biblioteca.bibliotecaCreate.dto.bookCategoryDTO.BookCategoryList;
import com.biblioteca.bibliotecaCreate.dto.bookCategoryDTO.BookCategoryRegister;
import com.biblioteca.bibliotecaCreate.dto.bookCategoryDTO.BookCategoryUpdate;
import com.biblioteca.bibliotecaCreate.infra.exception.NotFoundException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
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

    @GetMapping
    public ResponseEntity<Page<BookCategoryList>> list(Pageable pageable){
        var page = repository.findAllByActiveTrue(pageable).map(BookCategoryList::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookCategoryDetail> detail(@PathVariable Long id){
        var bookCategory = repository.findById(id)
                .orElseThrow(()-> new NotFoundException("Book category not found"));

        return ResponseEntity.ok(new BookCategoryDetail(bookCategory));
    }


    @PutMapping
    @Transactional
    public ResponseEntity<BookCategoryUpdate> update(@RequestBody @Valid BookCategoryUpdate update){
        var bookCategory = repository.findById(update.id())
                .orElseThrow(()->new EntityNotFoundException("Book Category not found"));
        bookCategory.updateBookCategory(update);

        return ResponseEntity.ok(new BookCategoryUpdate(bookCategory));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity delete(@PathVariable Long id){
        var bookCategory = repository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Book category not found"));
        bookCategory.delete();

        return ResponseEntity.noContent().build();
    }
}