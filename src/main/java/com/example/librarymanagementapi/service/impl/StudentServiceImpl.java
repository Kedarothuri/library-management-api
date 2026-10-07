package com.example.librarymanagementapi.service.impl;

import com.example.librarymanagementapi.dto.response.StudentResponse;
import com.example.librarymanagementapi.model.RegistrationStatus;
import com.example.librarymanagementapi.model.Student;
import com.example.librarymanagementapi.repository.StudentRepository;
import com.example.librarymanagementapi.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    private StudentResponse mapToResponse(Student student) {
        StudentResponse response = new StudentResponse();
        response.setId(student.getId());
        response.setName(student.getName());
        response.setEmail(student.getEmail());
        response.setStudentCardId(student.getStudentCardId());
        response.setStatus(student.getStatus());
        return response;
    }

    @Override
    public StudentResponse registerStudent(String name, String email, String studentCardId) {
        Student student = new Student(name, email, studentCardId);
        Student saved = studentRepository.save(student);
        return mapToResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public List<StudentResponse> getStudentsByStatus(RegistrationStatus status) {
        return studentRepository.findByStatus(status)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<StudentResponse> getAllStudents() {
        return studentRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public StudentResponse updateStatus(Long id, RegistrationStatus status) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));
        student.setStatus(status);
        Student updated = studentRepository.save(student);
        return mapToResponse(updated);
    }
}