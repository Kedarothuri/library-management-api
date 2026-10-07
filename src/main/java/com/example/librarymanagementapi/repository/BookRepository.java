package com.example.librarymanagementapi.repository;

import com.example.librarymanagementapi.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    // Eagerly fetches the Member record associated with the borrowed book
    @Query("SELECT b FROM Book b LEFT JOIN FETCH b.borrowedBy")
    List<Book> findAllWithBorrower();
}
