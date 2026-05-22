package com.biblioteca.bibliotecaCreate.Service.bookService;

import com.biblioteca.bibliotecaCreate.Entity.book.Book;
import com.biblioteca.bibliotecaCreate.Repository.BookCategoryRepository;
import com.biblioteca.bibliotecaCreate.Repository.BookRepository;
import com.biblioteca.bibliotecaCreate.dto.bookDTO.DataDetailBook;
import com.biblioteca.bibliotecaCreate.dto.bookDTO.DataListBook;
import com.biblioteca.bibliotecaCreate.dto.bookDTO.DataRegisterBook;
import com.biblioteca.bibliotecaCreate.dto.bookDTO.DataUpdateBook;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookCategoryRepository bookCategoryRepository;

    @Transactional
    public DataDetailBook register(DataRegisterBook registerBook){
        var category = bookCategoryRepository.findById(registerBook.categoryId())
                .orElseThrow(()-> new EntityNotFoundException("Category not found"));

        var book = bookRepository.save(new Book(registerBook, category));
        return new DataDetailBook(book);
    }

    public Page<DataListBook> list(Pageable pageable){
        return bookRepository.findAll(pageable)
                .map(DataListBook::new);
    }

    public DataDetailBook detail(Long id){
        var book = bookRepository.findById(id)
                .orElseThrow(()->new EntityNotFoundException("Book not found"));
        return new DataDetailBook(book);
    }

    @Transactional
    public DataUpdateBook update(Long id, DataUpdateBook dataUpdateBook){
        var book = bookRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Book not found"));
        return new DataUpdateBook(book);
    }

    @Transactional
    public void delete(Long id){
        var book = bookRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Book not found"));
        book.delete();
    }
}