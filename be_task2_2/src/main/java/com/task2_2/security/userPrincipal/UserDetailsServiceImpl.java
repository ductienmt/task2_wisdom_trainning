package com.task2_2.security.userPrincipal;

import com.task2_2.entities.Account;
import com.task2_2.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private AccountRepository accountRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = this.accountRepository.findByUsername(username);
        if (account == null) {
            throw new UsernameNotFoundException("Không tìm thấy tài khoản có username: " + username + " trong hệ thống");
        }
        return UserPrincipal.create(account);
    }
}
