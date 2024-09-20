package com.task2_2.service;

import com.task2_2.dto.AccountDTO;

import java.util.List;

public interface AccountService {
    AccountDTO save(Integer id, AccountDTO accountDTO);
    void delete(Integer id);
    List<AccountDTO> findAll();
    AccountDTO findById(Integer id);
    AccountDTO findByUsername(String username);
}
