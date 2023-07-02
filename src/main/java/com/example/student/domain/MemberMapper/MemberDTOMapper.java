package com.example.student.domain.MemberMapper;

import com.example.student.domain.Member;
import com.example.student.domain.request.MemberDto;

import java.util.function.Function;

public class MemberDTOMapper implements Function<Member, MemberDto> {
    @Override
    public MemberDto apply(Member member) {
        return new MemberDto(
                member.getUuid(),
                member.getFirstName(),
                member.getLastName(),
                member.getRole()
        );
    }
}
