package com.example.librarymanagementapi.repository;

import com.example.librarymanagementapi.model.BorrowLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface BorrowLogRepository extends JpaRepository<BorrowLog, Long> {
    Optional<BorrowLog> findFirstByBook_IdAndReturnedAtIsNullOrderByBorrowedAtDesc(Long bookId);
}
