package com.ul.vrs.controller;

import com.ul.vrs.entity.account.Account;
import com.ul.vrs.service.AccountManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class HomeController {
    @Autowired
    private AccountManagerService accountManager;

    @GetMapping("/")
    @ResponseBody
    public String home() {
        return "Welcome to the Vehicle Rental System!";
    }

    @GetMapping("/home")
    public String homePage() {
        return "home";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, Model model) {
        Account account = accountManager.logIn(username, password);

        if (account != null) {
            return "redirect:/home";
        } else {
            model.addAttribute("error", "Invalid username or password");
            return "login";
        }
    }

    @PostMapping("/signup")
    public String signUp(@RequestParam String username, @RequestParam String password, @RequestParam String accountType, Model model) {
        Account account = accountManager.signUp(username, password, accountType);

        if (account != null) {
            model.addAttribute("message", "Account created successfully!");
            return "login";
        } else {
            model.addAttribute("error", "Failed to create account. Please try again.");
            return "signup";
        }
    }
}
