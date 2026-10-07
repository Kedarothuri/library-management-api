package com.example.librarymanagementapi.service;

import com.example.librarymanagementapi.dto.request.MemberRequest;
import com.example.librarymanagementapi.dto.response.MemberResponse;
import java.util.List;

public interface MemberService {
    List<MemberResponse> getAllMembers();
    MemberResponse registerMember(MemberRequest request);
}
