package com.example.librarymanagementapi.controller;

import com.example.librarymanagementapi.dto.response.StudentResponse;
import com.example.librarymanagementapi.model.RegistrationStatus;
import com.example.librarymanagementapi.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
@CrossOrigin(origins = "*")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping("/register")
    public ResponseEntity<StudentResponse> register(
            @RequestParam String name,
            @RequestParam String email,
            @RequestParam String studentCardId) {
        return ResponseEntity.ok(studentService.registerStudent(name, email, studentCardId));
    }

    @GetMapping
    public ResponseEntity<List<StudentResponse>> getAllStudents(@RequestParam(required = false) RegistrationStatus status) {
        if (status != null) {
            return ResponseEntity.ok(studentService.getStudentsByStatus(status));
        }
        return ResponseEntity.ok(studentService.getAllStudents());
    }

    @PutMapping("/{id}/approve")
    public ResponseEntity<StudentResponse> approveStudent(@PathVariable Long id) {
        return ResponseEntity.ok(studentService.updateStatus(id, RegistrationStatus.APPROVED));
    }

    @PutMapping("/{id}/reject")
    public ResponseEntity<StudentResponse> rejectStudent(@PathVariable Long id) {
        return ResponseEntity.ok(studentService.updateStatus(id, RegistrationStatus.REJECTED));
    }
}