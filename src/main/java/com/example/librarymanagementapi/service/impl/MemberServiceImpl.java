package com.example.librarymanagementapi.service.impl;

import com.example.librarymanagementapi.dto.request.MemberRequest;
import com.example.librarymanagementapi.dto.response.MemberResponse;
import com.example.librarymanagementapi.model.Member;
import com.example.librarymanagementapi.repository.MemberRepository;
import com.example.librarymanagementapi.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class MemberServiceImpl implements MemberService {
    @Autowired
    private MemberRepository memberRepository;

    @Override
    public List<MemberResponse> getAllMembers() {
        return memberRepository.findAll().stream().map(m -> {
            MemberResponse res = new MemberResponse();
            res.setId(m.getId());
            res.setName(m.getName());
            res.setEmail(m.getEmail());
            return res;
        }).collect(Collectors.toList());
    }

    @Override
    public MemberResponse registerMember(MemberRequest request) {
        Member member = new Member(request.getName(), request.getEmail());
        Member saved = memberRepository.save(member);

        MemberResponse res = new MemberResponse();
        res.setId(saved.getId());
        res.setName(saved.getName());
        res.setEmail(saved.getEmail());
        return res;
    }
}
