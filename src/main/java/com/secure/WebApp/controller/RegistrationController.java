package com.secure.WebApp.controller;

import com.secure.WebApp.service.UserRegistrationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RegistrationController {

    private final UserRegistrationService registrationService;

    public RegistrationController(UserRegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @GetMapping("/register")
    public String showRegistrationForm() {
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@RequestParam String username,
                               @RequestParam String password,
                               Model model) {
        if (username == null || username.isBlank() || password == null || password.isBlank()) {
            model.addAttribute("error", "Username and password must not be empty.");
            return "register";
        }

        boolean success = registrationService.register(username.trim(), password);
        if (!success) {
            model.addAttribute("error", "Username already exists. Choose a different one.");
            return "register";
        }

        return "redirect:/login?registered";
    }
}
