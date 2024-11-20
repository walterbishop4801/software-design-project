package com.ul.vrs.service;

import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    // TODO: Replace with actual verification logic (database or hardcoded)
    public boolean authenticate(String username, String password) {
        return "user".equals(username) && "password".equals(password);
    }
}
