package com.example.librarymanagementapi.dto.response;

public class BookResponse {
    private Long id;
    private String title;
    private String author;
    private boolean available;
    private String borrowerName; // MUST match this exact field name!

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }
    public boolean isAvailable() { return available; }
    public void setAvailable(boolean available) { this.available = available; }

    // THESE GETTERS AND SETTERS ARE REQUIRED FOR JSON SERIALIZATION:
    public String getBorrowerName() { return borrowerName; }
    public void setBorrowerName(String borrowerName) { this.borrowerName = borrowerName; }
}