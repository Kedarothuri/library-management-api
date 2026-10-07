package com.example.librarymanagementapi.repository;

import com.example.librarymanagementapi.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface MemberRepository extends JpaRepository<Member,Long>{
}
