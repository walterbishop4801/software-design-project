package com.ul.vrs.service;

import com.ul.vrs.entity.account.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private AccountManagerService accountManagerService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountManagerService.getAccount(username);

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();;
        if (account == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return new User(account.getUsername(), 
                        passwordEncoder.encode(account.getPassword()), 
                        new ArrayList<>());
    }
}