package com.spring.project.dto;

import lombok.*;

/**
 * @author Andrii Barsuk
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {
    private long userId;
    private String firstName;
    private String lastName;
    private String email;
}
