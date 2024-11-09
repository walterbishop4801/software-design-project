package com.ul.vrs.service;

import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    public boolean authenticate(String username, String password) {
        // Replace with actual verification logic (database or hardcoded)
        return "user".equals(username) && "password".equals(password);
    }
}
