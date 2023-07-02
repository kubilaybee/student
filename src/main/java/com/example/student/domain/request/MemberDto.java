package com.example.student.domain.request;

import com.example.student.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberDto {

    private int uuid;
    private String firstName;
    private String lastName;
    private Role role;

}
