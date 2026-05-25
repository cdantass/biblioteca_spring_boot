package com.biblioteca.bibliotecaCreate.Service.bookCategoryService;

import com.biblioteca.bibliotecaCreate.Entity.book.BookCategory;
import com.biblioteca.bibliotecaCreate.Repository.BookCategoryRepository;
import com.biblioteca.bibliotecaCreate.Repository.BookRepository;
import com.biblioteca.bibliotecaCreate.dto.bookCategoryDTO.BookCategoryDetail;
import com.biblioteca.bibliotecaCreate.dto.bookCategoryDTO.BookCategoryList;
import com.biblioteca.bibliotecaCreate.dto.bookCategoryDTO.BookCategoryRegister;
import com.biblioteca.bibliotecaCreate.dto.bookCategoryDTO.BookCategoryUpdate;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookCategoryService {

    @Autowired
    private BookCategoryRepository bookCategoryRepository;

    @Autowired
    private BookRepository bookRepository;

    @Transactional
    public BookCategoryDetail register(BookCategoryRegister bookCategoryRegister){
        var bookCategory = bookCategoryRepository.save(new BookCategory(bookCategoryRegister));
        return new BookCategoryDetail(bookCategory);
    }

    public Page<BookCategoryList> list(Pageable pageable){
        return bookCategoryRepository.findAll(pageable)
                .map(BookCategoryList::new);
    }

    public BookCategoryDetail detail(Long id){
        var categoryId = bookCategoryRepository.findById(id)
                .orElseThrow(()->new EntityNotFoundException("Book Category not found"));
        return new BookCategoryDetail(categoryId);
    }

    @Transactional
    public BookCategoryUpdate update(Long id, BookCategoryUpdate bookCategoryUpdate){
        var category = bookCategoryRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Book Category not found"));
        return new BookCategoryUpdate(category);
    }

    @Transactional
    public void delete(Long id){
        var category = bookCategoryRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Book Category not found"));
        category.delete();
    }
}