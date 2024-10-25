package com.autoworks.rentals;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

    @GetMapping("/") // Maps the root URL to this method
    @ResponseBody
    public String home() {
        return "Welcome to the Vehicle Rental System!";
    }

    @GetMapping("/home") // Maps the "/home" URL as well
    @ResponseBody
    public String homePage() {
        return "This is the home page of the Vehicle Rental System.";
    }
}
