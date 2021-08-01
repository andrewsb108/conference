package com.spring.project.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {
    private long id;
    private String firstName;
    private String surname;
    private String email;
    private String password;
    private String phoneNumber;
    private Set<RoleDto> roles;
}
