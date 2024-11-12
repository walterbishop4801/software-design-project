package com.ul.vrs.entity.account;

import java.util.HashMap;
import java.util.Map;

public class AccountManager {
    private static AccountManager instance;
    private Map<String, Customer> accounts;  // Store customer accounts by customer ID

    // Private constructor to prevent instantiation from other classes
    private AccountManager() {
        accounts = new HashMap<>();
    }

    // Method to get the single instance of AccountManager
    public static synchronized AccountManager getInstance() {
        if (instance == null) {
            instance = new AccountManager();
        }
        return instance;
    }

    // Method to create a new customer account and add it to the accounts map
    public Customer createAccount(String customerId, String name) {
        Customer customer = new Customer(customerId, name);
        accounts.put(customerId, customer);
        System.out.println("Account created for customer ID: " + customerId);
        return customer;
    }

    // Method to retrieve a customer account by customer ID
    public Customer getAccount(String customerId) {
        return accounts.get(customerId);
    }

 // Method to change the state of a customer's account
    public void changeAccountState(String customerId, Account newState) {
        Customer customer = accounts.get(customerId);
        if (customer != null) {
            customer.setState(newState);
            System.out.println("Account state changed for customer ID: " + customerId);
        } else {
            System.out.println("Account not found for customer ID: " + customerId);
        }
    }
}