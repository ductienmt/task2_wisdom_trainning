package com.task2_2.service.impl;

import com.task2_2.dto.AccountDTO;
import com.task2_2.entities.Account;
import com.task2_2.exception.CustomException;
import com.task2_2.repositories.AccountRepository;
import com.task2_2.service.AccountService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public AccountDTO save(Integer id, AccountDTO accountDTO) {
        if (accountDTO == null || accountDTO.getUsername() == null || accountDTO.getPassword() == null || accountDTO.getRole() == null){
            throw new CustomException("Thông tin tài khoản không được để trống");
        }

        if (id != null) {
            // Update existing account
            Account account = this.accountRepository.findById(id)
                    .orElseThrow(() -> new CustomException("Không tìm thấy tài khoản với ID: " + id));

            if (!id.equals(accountDTO.getId())) {
                throw new CustomException("ID không trùng khớp");
            }

            account.setUsername(accountDTO.getUsername());
            account.setPassword(this.passwordEncoder.encode(accountDTO.getPassword()));
            account.setRole(accountDTO.getRole());

            this.accountRepository.save(account);
            return this.modelMapper.map(account, AccountDTO.class);
        } else {
            // Create new account
            Account account = this.accountRepository.findByUsername(accountDTO.getUsername());
            if (account != null) {
                throw new CustomException("Tài khoản đã tồn tại vui lòng đổi username");
            } else {
                Account newAccount = this.modelMapper.map(accountDTO, Account.class);
                newAccount.setPassword(this.passwordEncoder.encode(accountDTO.getPassword()));
                this.accountRepository.save(newAccount);
                return this.modelMapper.map(newAccount, AccountDTO.class);
            }
        }
    }


    @Override
    public void delete(Integer id) {
        if (id != null) {
            Account account = this.accountRepository.findById(id).orElse(null);
            if (account != null) {
                this.accountRepository.delete(account);
            } else {
                throw new CustomException("Không tìm thấy tài khoản");
            }
        } else {
            throw new CustomException("Kiểm tra lại id người dùng");
        }
    }

    @Override
    public List<AccountDTO> findAll() {
        return this.accountRepository.findAll().stream().map(account -> this.modelMapper.map(account, AccountDTO.class)).collect(Collectors.toList());
    }

    @Override
    public AccountDTO findById(Integer id) {
        if (id != null) {
            Account account = this.accountRepository.findById(id).orElse(null);
            if (account != null) {
                return this.modelMapper.map(account, AccountDTO.class);
            }
        }
        throw new CustomException("Không tìm thấy tài khoản");
    }

    @Override
    public AccountDTO findByUsername(String username) {
        return this.modelMapper.map(this.accountRepository.findByUsername(username), AccountDTO.class);
    }
}
