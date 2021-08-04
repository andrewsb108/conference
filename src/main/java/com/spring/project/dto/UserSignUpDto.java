package com.spring.project.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * @author Andrii Barsuk
 */
@Data
public class UserSignUpDto {
    @NotBlank(message="Email is required")
    @Email
    private String email;

    @NotBlank(message="Password is required")
    private String password;

    @NotBlank(message="FirstName is required")
    private String firstName;

    @NotBlank(message="LastName is required")
    private String lastName;


}
