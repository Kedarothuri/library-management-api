package com.example.librarymanagementapi.model;

import jakarta.persistence.*;

@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String author;
    private boolean available = true;

    // Change Member to Student here!
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member borrowedBy;



    public Book() {}

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
        this.available = true;
    }

    public Long getId() { return id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }
    public boolean isAvailable() { return available; }
    public void setAvailable(boolean available) { this.available = available; }

    // Update Getters and Setters to use Student
    public Member getBorrowedBy() { return borrowedBy; }
    public void setBorrowedBy(Member borrowedBy) { this.borrowedBy = borrowedBy; }
}