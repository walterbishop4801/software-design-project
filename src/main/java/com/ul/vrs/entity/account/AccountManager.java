package com.ul.vrs.entity.account;

import java.util.HashMap;
import java.util.Map;

public class AccountManager {
    private Map<String, Account> accounts;  // Stores accounts by username

    public AccountManager() {
        accounts = new HashMap<>();
    }

 // Sign up method that accepts different account types
    public Account signUp(String username, String password, String accountType) {
        if (accounts.containsKey(username)) {
            System.out.println("Username already exists: " + username);
            return null;
        }

        Account newAccount;
        String accountId = "ID" + (accounts.size() + 1);

        // Only support Customer accounts for now
        if (accountType == null || accountType.equalsIgnoreCase("customer")) {
            newAccount = new Customer(username, accountId, password);  // No cast needed
        } else {
            System.out.println("Currently, only Customer accounts are supported.");
            return null;
        }

        accounts.put(username, newAccount);
        System.out.println("Account created for username: " + username + " as " + accountType);
        return newAccount;
    }


    // Login method for all account types
    public Account logIn(String username, String password) {
        Account account = accounts.get(username);
        if (account != null && account.getPassword().equals(password)) {
            System.out.println("Login successful for user: " + username);
            return account;
        }
        System.out.println("Login failed for user: " + username);
        return null;
    }

    // Retrieve an account by username
    public Account getAccount(String username) {
        return accounts.get(username);
    }
}
