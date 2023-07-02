package com.example.student.Service;

import com.example.student.Repository.MemberRepo;
import com.example.student.domain.MemberMapper.MemberDTOMapper;
import com.example.student.domain.request.MemberDto;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MemberService {

    private final MemberRepo memberRepo;
    private final MemberDto memberDto;
    private final MemberDTOMapper memberDTOMapper;

    public MemberService(MemberRepo memberRepo, MemberDto memberDto, MemberDTOMapper memberDTOMapper) {
        this.memberRepo = memberRepo;
        this.memberDto = memberDto;
        this.memberDTOMapper = memberDTOMapper;
    }

    public List<MemberDto> getAllMembers(){
        return memberRepo.findAll().stream().map(memberDTOMapper).collect(Collectors.toList());
    }
//TODO: Add the transactions
}
