package com.example.student.service;

import com.example.student.exception.DataNotFoundException;
import com.example.student.exception.PermissionException;
import com.example.student.repository.MemberRepo;
import com.example.student.domain.model.Member;
import com.example.student.domain.request.MemberRequestDto;
import com.example.student.domain.response.MemberResponseDto;
import com.example.student.enums.Role;
import com.example.student.util.ErrorMessages;
import com.example.student.util.converter.ModelMapperUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class MemberService {

    private MemberRepo memberRepo;

    public MemberService(MemberRepo memberRepo) {
        this.memberRepo = memberRepo;
    }


    @Transactional
    public void addMember(MemberRequestDto memberRequestDto){
        Member member = ModelMapperUtils.map(memberRequestDto,Member.class);
        member.setUuid(UUID.randomUUID().toString());
        memberRepo.saveAndFlush(member);
    }

    public MemberResponseDto findByUuid(String uuid){
        Member member = memberRepo.findByUuid(uuid).orElseThrow(()->new DataNotFoundException(ErrorMessages.MEMBER_NOT_FOUND.getValue()));
        return ModelMapperUtils.map(member, MemberResponseDto.class);
    }

    public List<MemberResponseDto> getAllMembers(){
        List<Member> memberList = memberRepo.findAll();
        return ModelMapperUtils.mapAll(memberList, MemberResponseDto.class);
    }

    @Transactional
    public MemberResponseDto updateMember(String memberUuid, MemberRequestDto memberRequestDto) {
        Member member = memberRepo.findByUuid(memberUuid).orElseThrow(()->new DataNotFoundException(ErrorMessages.MEMBER_NOT_FOUND.getValue()));
        ModelMapperUtils.map(memberRequestDto,member);
        Member updatedMember = memberRepo.saveAndFlush(member);
        return ModelMapperUtils.map(updatedMember, MemberResponseDto.class);
    }

    public void deleteByUuid(String currentMemberUuid, String targetMemberUuid){
        Member currentMember = memberRepo.findByUuid(currentMemberUuid).orElseThrow(()->new DataNotFoundException(ErrorMessages.MEMBER_NOT_FOUND.getValue()));
        if (currentMember.getRole()!=Role.ADMIN){
            throw  new PermissionException(ErrorMessages.UNAUTHORIZED_MEMBER.getValue());
        }
        memberRepo.deleteByUuid(targetMemberUuid).orElseThrow(()->new DataNotFoundException(ErrorMessages.MEMBER_NOT_FOUND.getValue()));

    }
}
