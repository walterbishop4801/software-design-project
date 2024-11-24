package com.ul.vrs.service;

import com.ul.vrs.entity.account.Customer;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class AccountManagerService {

    private final Map<String, Customer> accounts = new HashMap<>(); 

    public Customer signUp(String name, String id, String password) {
        if (accounts.containsKey(name)) {
        		System.out.println("Username already exists: " + name);
            return null;
        }

        Customer newAccount = new Customer(name, id, password);  // Creates a new Customer account

        accounts.put(name, newAccount);
        System.out.println("Account created for username: " + name);
        return newAccount;
    }

    // Login method for all account types
    public Optional<Customer> logIn(String username, String password) {
    	Customer account = accounts.get(username);
        if (account != null && account.getPassword().equals(password)) {
            System.out.println("Login successful for user: " + username);
            return Optional.of(account);
        }
        System.out.println("Login failed for user: " + username);
        return Optional.empty();
    }

    // Retrieve an account by username
    public Optional<Customer> getAccount(String username) {
        return Optional.ofNullable(accounts.get(username));
    }
}
