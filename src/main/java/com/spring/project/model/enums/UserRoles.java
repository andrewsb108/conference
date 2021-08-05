package com.spring.project.model.enums;

import org.springframework.security.core.GrantedAuthority;

/**
 * @author Andrii Barsuk
 */
public enum UserRoles implements GrantedAuthority {
    MODERATOR, SPEAKER, USER;

    @Override
    public String getAuthority() {
        return name();
    }
}

