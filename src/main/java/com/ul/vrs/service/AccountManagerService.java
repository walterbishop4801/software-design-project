package com.ul.vrs.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ul.vrs.entity.account.Account;
import com.ul.vrs.entity.account.Customer;
import com.ul.vrs.entity.account.Manager;
import com.ul.vrs.entity.booking.Booking;
import com.ul.vrs.entity.booking.payment.strategy.PaymentStrategy;
import com.ul.vrs.repository.AccountRepository;

@Service
public class AccountManagerService {

    @Autowired
    AccountRepository accountRepository;

    private Map<String, Account> accounts = new HashMap<>();

    public Account signUp(String username, String password, String accountType) {

        Optional<Account> account = accountRepository.findById(username);

        if(account.isPresent()) {
            System.out.println("Username already exists: " + username);
            return null;
        }

        Account newAccount = null;

        switch (accountType.toLowerCase()) {
            case "customer" -> newAccount = new Customer(username, password);
            case "manager" -> newAccount = new Manager(username,  password);
            default -> System.err.println("Unkown account type");
        }

        if (newAccount != null) {
            accountRepository.save(newAccount);
            System.out.println("Account created for username: " + username + " as " + accountType);
        }

        return newAccount;
    }

    public Account logIn(String username, String password) {
        Optional<Account> account = accountRepository.findById(username);

        if (account.isPresent() && account.get().getPassword().equals(password)) {
            System.out.println("Login successful for user: " + username);
            return account.get();
        }

        System.out.println("Login failed for user: " + username);
        return null;
    }

    public Account getAccount(String username) {
        return accountRepository.findById(username).get();
    }
}