package com.ul.vrs.entity.account;

public class Customer extends Account {

    // Constructor
    public Customer(String username, String accountId, String password) {
        super(username, accountId, password);  // Call the superclass (Account) constructor
    }

    @Override
    public String toString() {
        return "Customer{" +
                "username='" + getUsername() + '\'' +
                ", accountId='" + getAccountId() + '\'' +
                '}';
    }
}
