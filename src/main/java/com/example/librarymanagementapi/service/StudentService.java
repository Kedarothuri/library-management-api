package com.example.librarymanagementapi.service;

import com.example.librarymanagementapi.dto.response.StudentResponse;
import com.example.librarymanagementapi.model.RegistrationStatus;

import java.util.List;

public interface StudentService {
    StudentResponse registerStudent(String name, String email, String studentCardId);
    List<StudentResponse> getStudentsByStatus(RegistrationStatus status);
    List<StudentResponse> getAllStudents();
    StudentResponse updateStatus(Long id, RegistrationStatus status);
}