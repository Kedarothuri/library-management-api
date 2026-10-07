package com.example.librarymanagementapi.mapper;

import com.example.librarymanagementapi.dto.request.BookRequest;
import com.example.librarymanagementapi.dto.response.BookResponse;
import com.example.librarymanagementapi.model.Book;
import org.springframework.stereotype.Component;

@Component
public class BookMapper {

    public Book toEntity(BookRequest request) {
        return new Book(request.getTitle(), request.getAuthor());
    }

    public BookResponse toResponse(Book book) {
        BookResponse response = new BookResponse();
        response.setId(book.getId());
        response.setTitle(book.getTitle());
        response.setAuthor(book.getAuthor());
        response.setAvailable(book.isAvailable());

        if (book.getBorrowedBy() != null) {
            response.setBorrowerName(book.getBorrowedBy().getName());
        } else {
            response.setBorrowerName(null);
        }

        return response;
    }
}