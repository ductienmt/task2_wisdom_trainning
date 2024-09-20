package com.task2_2.service.impl;

import com.task2_2.dto.AccountDTO;
import com.task2_2.dto.AuthRequest;
import com.task2_2.dto.AuthResponse;
import com.task2_2.entities.Account;
import com.task2_2.exception.CustomException;
import com.task2_2.security.jwt.JwtProvider;
import com.task2_2.security.userPrincipal.UserPrincipal;
import com.task2_2.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private AccountServiceImpl accountService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtProvider jwtProvider;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public AccountDTO register(AccountDTO accountDTO) {
        try {
            return accountService.save(accountDTO.getId(), accountDTO);
        } catch (Exception e) {
            throw new CustomException(e.getMessage());
        }

    }

    @Override
    public AuthResponse login(AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtProvider.generateToken(authentication);
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        AuthResponse authResponse = new AuthResponse();
        authResponse.setToken(token);
        authResponse.setUsername(userPrincipal.getUsername());
        authResponse.setRole(userPrincipal.getAuthorities());
        return authResponse;

    }
}
