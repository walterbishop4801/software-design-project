package com.ul.vrs.entity.account;

import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Account {
    @Id
	private String username;
	private String password;
	
	//Constructor
	public Account(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	//Getters
	public String getUsername() {
		return username;
	}
	
	public String getPassword() {
		return password;
	}
	
	//Setters
	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}