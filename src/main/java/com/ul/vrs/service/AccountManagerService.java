package com.ul.vrs.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.ul.vrs.entity.account.Account;
import com.ul.vrs.entity.account.Customer;

@Service
public class AccountManagerService {
    private Map<String, Account> accounts = new HashMap<>();

    public Account signUp(String username, String password, String accountType) {
        if (accounts.containsKey(username)) {
            System.out.println("Username already exists: " + username);
            return null;
        }

        Account newAccount;
        String accountId = "ID" + (accounts.size() + 1);

        // TODO: Include rest of the account types (can be encapsulated using the factory design pattern)
        if (accountType == null || accountType.equalsIgnoreCase("customer")) {
            newAccount = new Customer(username, accountId, password);
        } else {
            System.out.println("Currently, only Customer accounts are supported.");
            return null;
        }

        accounts.put(username, newAccount);
        System.out.println("Account created for username: " + username + " as " + accountType);
        return newAccount;
    }

    public Account logIn(String username, String password) {
        Account account = accounts.get(username);

        if (account != null && account.getPassword().equals(password)) {
            System.out.println("Login successful for user: " + username);
            return account;
        }

        System.out.println("Login failed for user: " + username);
        return null;
    }

    public Account getAccount(String username) {
        return accounts.get(username);
    }
}
