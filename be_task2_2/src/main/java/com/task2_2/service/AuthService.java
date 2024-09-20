package com.task2_2.service;

import com.task2_2.dto.AccountDTO;
import com.task2_2.dto.AuthRequest;
import com.task2_2.dto.AuthResponse;

public interface AuthService {
    AccountDTO register(AccountDTO accountDTO);
    AuthResponse login(AuthRequest authRequest);
}
