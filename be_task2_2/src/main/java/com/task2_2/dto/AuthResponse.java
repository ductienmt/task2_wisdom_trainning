package com.task2_2.dto;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import java.util.Collection;

@Data
public class AuthResponse {
    private String username;
    private String token;
    private Collection<? extends GrantedAuthority> role;
}
