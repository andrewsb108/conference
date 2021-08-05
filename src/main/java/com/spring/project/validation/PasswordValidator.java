package com.spring.project.validation;

import com.spring.project.dto.UserSignUpDto;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class PasswordValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return UserSignUpDto.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        UserSignUpDto user = (UserSignUpDto) target;
        if (!user.getMatchingPassword().equals(user.getPassword())) {
            errors.rejectValue("matchingPassword", "valid.reg.password.matching");
        }
    }
}