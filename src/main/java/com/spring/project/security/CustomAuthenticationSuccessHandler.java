package com.spring.project.security;

import com.spring.project.model.enums.Role;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private static Set<String> NONE_ADMIN_ROLES = Set.of(Role.USER.name(), Role.SPEAKER.name());

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException {
        response.setStatus(HttpServletResponse.SC_OK);

        for (GrantedAuthority auth : authentication.getAuthorities()) {
            if (Role.MODERATOR.name().equals(auth.getAuthority())) {
                response.sendRedirect("/event/all");
                return;
            } else if (NONE_ADMIN_ROLES.contains(auth.getAuthority())) {
                response.sendRedirect("/index");
                return;
            }
        }
    }
}
