package com.task2_2.controller;

import com.task2_2.dto.AccountDTO;
import com.task2_2.dto.AuthRequest;
import com.task2_2.service.impl.AuthServiceImpl;
import com.task2_2.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthServiceImpl authService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody AccountDTO accountDTO) {
        try {
            return ResponseUtil.successAuth("Đăng ký thành công",authService.register(accountDTO));
        } catch (Exception e) {
            return ResponseUtil.failAuth("Đăng ký thất bại",e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest authRequest) {
        try {
            return ResponseUtil.successAuth("Đăng nhập thành công",authService.login(authRequest));
        } catch (Exception e) {
            return ResponseUtil.failAuth("Đăng nhập thất bại",e.getMessage());
        }
    }
}
