package com.ul.vrs.controller;

import com.ul.vrs.service.AuthenticationService;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {
    // TODO: Change this so Account package is used
    @Autowired
    private AuthenticationService authenticationService;

    @GetMapping("/")
    @ResponseBody
    public String home() {
        return "Welcome to the Vehicle Rental System!!";
    }

    // Mapping for home page
    @GetMapping("/home")
    public String homePage() {
        return "home";
    }

 	// Mapping for login page
    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/logout")
    public String logout() {
        return "redirect:/login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, Model model) {
        if (authenticationService.authenticate(username, password)) {
            return "redirect:/home";
        } else {
            model.addAttribute("error", "Invalid username or password");
            return "login";
        }
    }
}