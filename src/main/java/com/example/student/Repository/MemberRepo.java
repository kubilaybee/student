package com.example.student.Repository;

import com.example.student.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepo extends JpaRepository<Member,Integer> {
    Optional<Member> findByUuid(Integer uuid);
}
