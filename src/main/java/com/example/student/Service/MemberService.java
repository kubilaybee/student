package com.example.student.Service;

import com.example.student.Exception.DataNotFoundException;
import com.example.student.Exception.PermissionException;
import com.example.student.Repository.MemberRepo;
import com.example.student.domain.Model.Member;
import com.example.student.domain.request.MemberRequestDto;
import com.example.student.domain.response.MemberResponseDto;
import com.example.student.enums.Role;
import com.example.student.util.ErrorMessages;
import com.example.student.util.converter.ModelMapperUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class MemberService {

    private MemberRepo memberRepo;
    private MemberRequestDto memberRequestDto;
    private ModelMapperUtils modelMapperUtils;

    public MemberService(MemberRepo memberRepo, MemberRequestDto memberRequestDto, ModelMapperUtils modelMapperUtils) {
        this.memberRepo = memberRepo;
        this.memberRequestDto = memberRequestDto;
        this.modelMapperUtils = modelMapperUtils;
    }


    @Transactional
    public void addMember(MemberRequestDto memberRequestDto){
        Member member = modelMapperUtils.map(memberRequestDto,Member.class);
        member.setUuid(UUID.randomUUID().toString());
        memberRepo.saveAndFlush(member);
    }

    public MemberResponseDto findByUuid(String uuid){
        Member member = memberRepo.findByUuid(uuid).orElseThrow(()->new DataNotFoundException(ErrorMessages.MEMBER_NOT_FOUND.getValue()));
        return modelMapperUtils.map(member, MemberResponseDto.class);
    }

    public List<MemberResponseDto> getAllMembers(){
        List<Member> memberList = memberRepo.findAll();
        return modelMapperUtils.mapAll(memberList, MemberResponseDto.class);
    }

    @Transactional
    public MemberResponseDto updateMember(String memberUuid, MemberRequestDto memberRequestDto) {
        Member member = memberRepo.findByUuid(memberUuid).orElseThrow(()->new DataNotFoundException(ErrorMessages.MEMBER_NOT_FOUND.getValue()));
        modelMapperUtils.map(memberRequestDto,member);
        Member updatedMember = memberRepo.saveAndFlush(member);
        return modelMapperUtils.map(updatedMember, MemberResponseDto.class);
    }

    public void deleteByUuid(String currentMemberUuid, String targetMemberUuid){
        Member currentMember = memberRepo.findByUuid(currentMemberUuid).orElseThrow(()->new DataNotFoundException(ErrorMessages.MEMBER_NOT_FOUND.getValue()));
        if (currentMember.getRole()!=Role.ADMIN){
            throw  new PermissionException(ErrorMessages.UNAUTHORIZED_MEMBER.getValue());
        }
        memberRepo.deleteByUuid(targetMemberUuid).orElseThrow(()->new DataNotFoundException(ErrorMessages.MEMBER_NOT_FOUND.getValue()));

    }
}
