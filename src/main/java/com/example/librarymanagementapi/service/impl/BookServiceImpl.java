package com.example.librarymanagementapi.service.impl;

import com.example.librarymanagementapi.dto.request.BookRequest;
import com.example.librarymanagementapi.dto.response.BookResponse;
import com.example.librarymanagementapi.mapper.BookMapper;
import com.example.librarymanagementapi.model.*;
import com.example.librarymanagementapi.repository.BookRepository;
import com.example.librarymanagementapi.repository.BorrowLogRepository;
import com.example.librarymanagementapi.repository.MemberRepository;
import com.example.librarymanagementapi.repository.StudentRepository;
import com.example.librarymanagementapi.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.librarymanagementapi.exception.ResourceNotFoundException;
import com.example.librarymanagementapi.exception.BookNotAvailableException;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService{
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private BookMapper bookMapper;

    @Autowired
    private BorrowLogRepository borrowLogRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Override
    @Transactional(readOnly = true)
    public List<BookResponse> getAllBooks() {
        return bookRepository.findAllWithBorrower()
                .stream()
                .map(bookMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public BookResponse addBook(BookRequest request) {
        Book book = bookMapper.toEntity(request);
        Book savedBook = bookRepository.save(book);
        return bookMapper.toResponse(savedBook);
    }

    @Override
    @Transactional
    public BookResponse borrowBook(Long bookId, Long studentId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found"));

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found"));

        if (student.getStatus() != RegistrationStatus.APPROVED) {
            throw new BookNotAvailableException("Student account is not approved by Admin yet");
        }

        if (!book.isAvailable()) {
            throw new BookNotAvailableException("Book is already borrowed");
        }

        book.setAvailable(false);
        book.setBorrowedBy(student); // Link student entity
        Book updatedBook = bookRepository.save(book);

        // Save Borrow Log entry
        borrowLogRepository.save(new BorrowLog(student, book));

        return bookMapper.toResponse(updatedBook);
    }

    @Override
    @Transactional
    public BookResponse returnBook(Long bookId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new ResourceNotFoundException("Book with ID " + bookId + " not found."));

        if (book.isAvailable()) {
            throw new BookNotAvailableException("Book is not currently borrowed");
        }
        borrowLogRepository.findFirstByBook_IdAndReturnedAtIsNullOrderByBorrowedAtDesc(bookId)
                .ifPresent(log -> {
                    log.setReturnedAt(LocalDateTime.now());
                    borrowLogRepository.save(log);
                });
        book.setAvailable(true);
        book.setBorrowedBy(null);
        Book updatedBook = bookRepository.save(book);
        return bookMapper.toResponse(updatedBook);
    }
}
