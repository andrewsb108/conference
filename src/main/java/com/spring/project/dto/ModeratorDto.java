package com.spring.project.dto;

import com.spring.project.model.Speaker;
import com.spring.project.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ModeratorDto {
    private long id;
    private String name;
    private List<Speaker> speakerList;
    private List<User> userList;
}
