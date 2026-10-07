package com.example.librarymanagementapi.repository;

import com.example.librarymanagementapi.model.Student;
import com.example.librarymanagementapi.model.RegistrationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findByStatus(RegistrationStatus status);
}