package com.ul.vrs.entity.account;

public abstract class Account {
    private String username;
    private String accountId;
    private String password;

    // Constructor
    public Account(String username, String accountId, String password) {
        this.username = username;
        this.accountId = accountId;
        this.password = password;
    }

    // Getters and Setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Account{" +
                "username='" + username + '\'' +
                ", accountId='" + accountId + '\'' +
                '}';
    }
}
