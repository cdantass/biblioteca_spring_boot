package com.biblioteca.bibliotecaCreate.Repository;

import com.biblioteca.bibliotecaCreate.Entity.book.BookCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookCategoryRepository extends JpaRepository<BookCategory, Long> {
    Page<BookCategory> findAllByActiveTrue(Pageable paginacao);

}
