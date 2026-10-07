package com.example.librarymanagementapi.controller;

import com.example.librarymanagementapi.dto.request.BookRequest;
import com.example.librarymanagementapi.dto.response.BookResponse;
import com.example.librarymanagementapi.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@CrossOrigin(origins = "*")
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping
    public ResponseEntity<List<BookResponse>> getAllBooks() {
        return ResponseEntity.ok(bookService.getAllBooks());
    }

    @PostMapping
    public ResponseEntity<BookResponse> addBook(@RequestBody BookRequest request) {
        return ResponseEntity.ok(bookService.addBook(request));
    }

    @PutMapping("/{bookId}/borrow/{memberId}")
    public ResponseEntity<BookResponse> borrowBook(@PathVariable Long bookId, @PathVariable Long memberId) {
        return ResponseEntity.ok(bookService.borrowBook(bookId, memberId));
    }

    @PutMapping("/{bookId}/return")
    public ResponseEntity<BookResponse> returnBook(@PathVariable Long bookId) {
        return ResponseEntity.ok(bookService.returnBook(bookId));
    }
}
