package com.example.student.domain.response;

import com.example.student.enums.Role;
import lombok.Data;

@Data
public class MemberResponseDto {

    private String uuid;
    private String firstName;
    private String lastName;
    private Role role;
}
