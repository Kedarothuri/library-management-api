package com.example.librarymanagementapi.model;

import jakarta.persistence.*;

@Entity
@Table(name = "students")
public class Student extends Member { // Student inherits from Member

    private String studentCardId;

    @Enumerated(EnumType.STRING)
    private RegistrationStatus status = RegistrationStatus.PENDING;

    public Student() {
        super();
    }

    public Student(String name, String email, String studentCardId) {
        super(name, email); // Passes name and email to Member base class
        this.studentCardId = studentCardId;
        this.status = RegistrationStatus.PENDING;
    }

    public String getStudentCardId() { return studentCardId; }
    public void setStudentCardId(String studentCardId) { this.studentCardId = studentCardId; }
    public RegistrationStatus getStatus() { return status; }
    public void setStatus(RegistrationStatus status) { this.status = status; }
}