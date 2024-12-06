package com.ul.vrs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import com.ul.vrs.entity.account.Account;
import com.ul.vrs.service.AccountManagerService;
import com.ul.vrs.service.CustomUserDetailsService;
import com.ul.vrs.security.JwtTokenUtil;

@RestController
@RequestMapping("/api/account")
public class AccountController {
    @Autowired
    private AccountManagerService accountManager = new AccountManagerService();

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    // Sign up - http://localhost:8080/api/account/signup?username=?,password=?,accountType=?
    @PostMapping("/signup")
    public ResponseEntity<Account> signUp(@RequestParam String username, @RequestParam String password, @RequestParam String accountType) {

        Account account = accountManager.signUp(username, password, accountType);

        if (account != null) {
            return ResponseEntity.ok(account);
        } else {
            return ResponseEntity.badRequest().body(null);
        }
    }

    // Log in - http://localhost:8080/api/account/login?username=?,password=?
    @PostMapping("/login")
    public ResponseEntity<String> logIn(@RequestParam String username, @RequestParam String password) {
        Account account = accountManager.logIn(username, password);
        if (account == null) {
            return ResponseEntity.badRequest().body("Invalid credentials");
        }

        // Generate JWT
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        String jwt = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(jwt);
    }
}