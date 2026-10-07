package com.example.librarymanagementapi.controller;

import com.example.librarymanagementapi.dto.request.MemberRequest;
import com.example.librarymanagementapi.dto.response.MemberResponse;
import com.example.librarymanagementapi.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/members")
@CrossOrigin(origins = "*")
public class MemberController {
    @Autowired
    private MemberService memberService;

    @GetMapping
    public ResponseEntity<List<MemberResponse>> getAllMembers() {
        return ResponseEntity.ok(memberService.getAllMembers());
    }

    @PostMapping
    public ResponseEntity<MemberResponse> registerMember(@RequestBody MemberRequest request) {
        return ResponseEntity.ok(memberService.registerMember(request));
    }
}
