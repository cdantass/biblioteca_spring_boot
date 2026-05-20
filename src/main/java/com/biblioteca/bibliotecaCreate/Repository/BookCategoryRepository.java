package com.biblioteca.bibliotecaCreate.Repository;

import com.biblioteca.bibliotecaCreate.Entity.book.BookCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookCategoryRepository extends JpaRepository<BookCategory, Long> {
}
