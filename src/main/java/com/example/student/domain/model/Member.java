package com.example.student.domain.model;

import com.example.student.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Data
@Table(name = "member")
@AllArgsConstructor
public class Member {

    @Id
    @SequenceGenerator(name = "member_id_seq",
            sequenceName = "member_id_seq",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator ="member_id_seq" )
    private int id;

    @Column(name = "uuid",nullable = false)
    private String uuid;

    @Column(name = "first_name",nullable = false)
    private String firstName;

    @Column(name = "last_name",nullable = false)
    private String lastName;

    @Column(name = "role",nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    public Member(String uuid, String firstName, String lastName, Role role) {
        this.uuid = uuid;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
    }
}
