package com.ul.vrs.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ul.vrs.entity.account.Account;
import com.ul.vrs.entity.account.AccountManager;

@RestController
@RequestMapping("/api/account")
public class AccountController {

   private AccountManager accountManager = new AccountManager();
   
   @PostMapping("/signup")
    public ResponseEntity<Account> signUp(@RequestParam String username, @RequestParam String password, @RequestParam String accountType) {
        Account account = accountManager.signUp(username, password, accountType);
        if (account != null) {
            return ResponseEntity.ok(account);
        } else {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<Account> logIn(@RequestParam String username, @RequestParam String password) {
        Account account = accountManager.logIn(username, password);
        if (account != null) {
            return ResponseEntity.ok(account);
        } else {
            return ResponseEntity.status(401).body(null);
        }
    }
}
