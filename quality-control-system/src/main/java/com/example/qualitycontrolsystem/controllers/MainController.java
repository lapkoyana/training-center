package com.example.qualitycontrolsystem.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.qualitycontrolsystem.entity.User;
import com.example.qualitycontrolsystem.service.UserService;

@Controller
public class MainController {
	@Autowired
	UserService userService;
	
	@GetMapping("/login")
	private String login() {
		return "login";
	}
	
	@GetMapping("/registration")
	public String registration() {
		return "registration";
	}
	
	@PostMapping("/registration")
	public String addUser(
			 User user,
			 Model model) {
		
		if (!userService.addUser(user)) {
            model.addAttribute("message", "Такой пользователь уже зарегистрирован!");
            return "registration";
        }

		
		return "redirect:/login";
	}
	
    @GetMapping
    public String main() {
        return "main";
    }
    
    @GetMapping("/greeting")
    public String greeting(
    		@AuthenticationPrincipal User user,
    		Model model) {
    	model.addAttribute("user", user);
        return "greeting";
    }
}
