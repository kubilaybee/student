package com.example.student.Repository;

import com.example.student.domain.Model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepo extends JpaRepository<Member,Integer> {
    Optional<Member> findByUuid(String uuid);
    Optional<Member> deleteByUuid(String uuid);
}
