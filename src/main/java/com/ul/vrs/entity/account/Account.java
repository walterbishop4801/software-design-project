package com.ul.vrs.entity.account;

public abstract class Account {
    private String username;
    private String accountId;
    private String password;

    public Account(String username, String accountId, String password) {
        this.username = username;
        this.accountId = accountId;
        this.password = password;
    }

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
}