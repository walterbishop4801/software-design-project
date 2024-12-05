package com.ul.vrs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ul.vrs.entity.account.Account;
import com.ul.vrs.service.AccountManagerService;


@RestController
@RequestMapping("/api/account")
public class AccountController {
    @Autowired
    private AccountManagerService accountManager = new AccountManagerService();

    // Sign up - http://localhost:8080/api/account/signup?username=?,password=?,accountType=?
    @PostMapping("/signup")
    public ResponseEntity<Account> signUp(@RequestParam String username, @RequestParam String password, @RequestParam String accountType) {
        if (accountManager.getLoggedAccount() == null) {
            Account account = accountManager.signUp(username, password, accountType);

            if (account != null) {
                return ResponseEntity.ok(account);
            } else {
                return ResponseEntity.badRequest().body(null);
            }
        }

        return ResponseEntity.badRequest().build();
    }

    // Log in - http://localhost:8080/api/account/login?username=?,password=?
    @PostMapping("/login")
    public ResponseEntity<Account> logIn(@RequestParam String username, @RequestParam String password) {
        if (accountManager.getLoggedAccount() == null) {
            Account account = accountManager.logIn(username, password);

            if (account != null) {
                return ResponseEntity.ok(account);
            } else {
                return ResponseEntity.badRequest().body(null);
            }
        }

        return ResponseEntity.badRequest().build();
    }

    // Log out - http://localhost:8080/api/account/logout
    @PostMapping("/logout")
    public ResponseEntity<String> logOut() {
        if (accountManager.logout()) {
            return ResponseEntity.ok("Account has been logged out");
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}