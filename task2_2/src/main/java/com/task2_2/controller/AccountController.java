package com.task2_2.controller;

import com.task2_2.dto.AccountDTO;
import com.task2_2.service.impl.AccountServiceImpl;
import com.task2_2.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {
    @Autowired
    private AccountServiceImpl accountService;

    @PostMapping("/save")
    public ResponseEntity<?> saveAccount(@RequestBody AccountDTO accountDTO) {
        try {
            return ResponseUtil.success(this.accountService.save(accountDTO.getId(), accountDTO));
        } catch (Exception e) {
            return ResponseUtil.fail(e.getMessage());
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateAccount(@RequestParam("id") Integer id, @RequestBody AccountDTO accountDTO) {
        try {
            return ResponseUtil.success(this.accountService.save(id, accountDTO));
        } catch (Exception e) {
            return ResponseUtil.fail(e.getMessage());
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteAccount(@RequestParam("id") Integer id) {
        try {
            this.accountService.delete(id);
            return ResponseUtil.success("Xóa tài khoản thành công");
        } catch (Exception e) {
            return ResponseUtil.fail(e.getMessage());
        }
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllAccounts() {
        try {
            return ResponseUtil.success(this.accountService.findAll());
        } catch (Exception e) {
            return ResponseUtil.fail(e.getMessage());
        }
    }
}
