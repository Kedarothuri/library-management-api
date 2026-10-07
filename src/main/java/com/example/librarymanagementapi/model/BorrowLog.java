package com.example.librarymanagementapi.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "borrow_logs")
public class BorrowLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "book_id")
    private Book book;

    private LocalDateTime borrowedAt;
    private LocalDateTime returnedAt;

    public BorrowLog() {}

    public BorrowLog(Student student, Book book) {
        this.student = student;
        this.book = book;
        this.borrowedAt = LocalDateTime.now();
    }

    public Long getId() { return id; }
    public Student getStudent() { return student; }
    public Book getBook() { return book; }
    public LocalDateTime getBorrowedAt() { return borrowedAt; }
    public LocalDateTime getReturnedAt() { return returnedAt; }
    public void setReturnedAt(LocalDateTime returnedAt) { this.returnedAt = returnedAt; }
}
