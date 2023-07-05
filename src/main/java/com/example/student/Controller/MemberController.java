package com.example.student.Controller;

import com.example.student.Service.MemberService;
import com.example.student.domain.request.MemberRequestDto;
import com.example.student.domain.response.MemberResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class MemberController {
    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/add-member")
    public ResponseEntity<String> addMember(@RequestBody MemberRequestDto memberRequestDto){
        memberService.addMember(memberRequestDto);
        return ResponseEntity.ok().body("Added!");
    }
    @GetMapping("/{uuid}")
    public ResponseEntity<MemberResponseDto> getMember(@PathVariable(value = "uuid")String uuid){
        MemberResponseDto memberResponse = memberService.findByUuid(uuid);
        return ResponseEntity.ok(memberResponse);
    }
    @GetMapping("/members")
    public ResponseEntity<List<MemberResponseDto>> getMembers(){
        List<MemberResponseDto> memberResponseDtoList = memberService.getAllMembers();
        return ResponseEntity.ok(memberResponseDtoList);
    }
    @PutMapping("/{uuid}")
    public ResponseEntity<MemberResponseDto> updateMember(@PathVariable(value = "uuid")String uuid,@RequestBody MemberRequestDto memberRequestDto){
        MemberResponseDto updatedMember = memberService.updateMember(uuid,memberRequestDto);
        return ResponseEntity.ok(updatedMember);
    }
    @DeleteMapping("/{uuid}")
    public ResponseEntity<String> deleteMember(@PathVariable(value = "uuid")String deleteMember,@RequestBody String currentMember){
        memberService.deleteByUuid(currentMember,deleteMember);
        return ResponseEntity.ok("Deleted!");
    }
}
