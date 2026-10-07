package com.example.librarymanagementapi.dto.response;

import com.example.librarymanagementapi.model.RegistrationStatus;

public class StudentResponse {
    private Long id;
    private String name;
    private String email;
    private String studentCardId;
    private RegistrationStatus status;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getStudentCardId() { return studentCardId; }
    public void setStudentCardId(String studentCardId) { this.studentCardId = studentCardId; }
    public RegistrationStatus getStatus() { return status; }
    public void setStatus(RegistrationStatus status) { this.status = status; }
}