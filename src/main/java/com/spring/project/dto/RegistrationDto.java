package com.spring.project.dto;

import com.spring.project.validation.ValidEmail;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 * @author Andrii Barsuk
 */
@Data
public class RegistrationDto {
    @NotEmpty(message = "{valid.reg.not.empty}")
    @Size(min = 2, max = 30, message = "{valid.reg.name.size}")
    private String firstName;

    @NotEmpty(message = "{valid.reg.not.empty}")
    @Size(min = 2, max = 30, message = "{valid.reg.name.size}")
    private String lastName;

    @NotEmpty(message = "{valid.reg.not.empty}")
    private String password;

    @NotEmpty(message = "{valid.reg.not.empty}")
    private String matchingPassword;

    @ValidEmail(message = "{valid.reg.email}")
    @NotEmpty(message = "{valid.reg.not.empty}")
    @Size(min = 5, max = 50, message = "{valid.reg.email.size}")
    private String email;
}
