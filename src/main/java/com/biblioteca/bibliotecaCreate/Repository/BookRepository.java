package com.biblioteca.bibliotecaCreate.Repository;

import com.biblioteca.bibliotecaCreate.Entity.book.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {

    boolean existsByIdAndActiveTrue(Long id);
}
