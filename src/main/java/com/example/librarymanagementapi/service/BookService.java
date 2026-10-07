package com.example.librarymanagementapi.service;

import com.example.librarymanagementapi.dto.request.BookRequest;
import com.example.librarymanagementapi.dto.response.BookResponse;
import java.util.List;

public interface BookService {
    List<BookResponse> getAllBooks();
    BookResponse addBook(BookRequest request);
    BookResponse borrowBook(Long BookId, Long memberId);
    BookResponse returnBook(Long bookId);
}
