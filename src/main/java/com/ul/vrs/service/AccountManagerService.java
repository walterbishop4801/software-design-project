package com.ul.vrs.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.ul.vrs.entity.account.Account;
import com.ul.vrs.entity.account.Customer;
import com.ul.vrs.entity.account.Manager;

@Service
public class AccountManagerService {
    // --------------------------------------------
    // Current logged account
    // --------------------------------------------
    private Account loggedAccount;

    public final Account getLoggedAccount() {
        return this.loggedAccount;
    }
    // --------------------------------------------

    private Map<String, Account> accounts = new HashMap<>();

    public Account signUp(String username, String password, String accountType) {
        if (accounts.containsKey(username)) {
            System.out.println("Username already exists: " + username);
            return null;
        }

        Account newAccount = null;
        String accountId = "ID" + (accounts.size() + 1);

        switch (accountType.toLowerCase()) {
            case "customer" -> newAccount = new Customer(username, accountId, password);
            case "manager" -> newAccount = new Manager(username, accountId, password);
            default -> System.err.println("Unkown account type");
        }

        if (newAccount != null) {
            accounts.put(username, newAccount);
            System.out.println("Account created for username: " + username + " as " + accountType);
        }

        return newAccount;
    }

    public Account logIn(String username, String password) {
        Account account = accounts.get(username);

        if (account != null && account.getPassword().equals(password)) {
            System.out.println("Login successful for user: " + username);
            loggedAccount = account;
            return account;
        }

        System.out.println("Login failed for user: " + username);
        return null;
    }

    public boolean logout() {
        if (loggedAccount != null) {
            loggedAccount = null;
            return true;
        }

        return false;
    }

    public Account getAccount(String username) {
        return accounts.get(username);
    }
}