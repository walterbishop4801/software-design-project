package com.ul.vrs.entity.account;

import jakarta.persistence.*;

@MappedSuperclass
public abstract class Account {
	private String username;
    @Id
	private String accountId;
	private String password;
	
	//Constructor
	public Account(String username, String accountId, String password) {
		this.username = username;
		this.accountId = accountId;
		this.password = password;
	}
	
	//Getters
	public String getUsername() {
		return username;
	}
	
	public String getAccountId() {
		return accountId;
	}
	
	public String getPassword() {
		return password;
	}
	
	//Setters
	public void setUsername(String username) {
		this.username = username;
	}
	
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
}