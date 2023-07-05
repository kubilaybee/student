package com.example.student.domain.request;

import com.example.student.enums.Role;
import lombok.Data;

@Data
public class MemberRequestDto {

    private String uuid;
    private String firstName;
    private String lastName;
    private Role role;

}
