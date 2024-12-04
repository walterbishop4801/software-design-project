package com.ul.vrs.entity.account;

import jakarta.persistence.*;

@MappedSuperclass
public abstract class Account {
    @Id
    private String accountId;
    private String username;
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