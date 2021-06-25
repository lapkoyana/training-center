package com.example.qualitycontrolsystem.controllers;

import java.util.Collections;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.qualitycontrolsystem.entity.Role;
import com.example.qualitycontrolsystem.entity.User;
import com.example.qualitycontrolsystem.repos.UserRepository;

@Controller
public class MainController {
	@Autowired
	private UserRepository userRepos;
	
	@GetMapping("/registration")
	public String registration() {
		return "registration";
	}
	
	@PostMapping("/registration")
	public String addUser(
			User user,
			 Map<String, Object> model) {
		User userFromDB = userRepos.findByUsername(user.getUsername());
		
		if (userFromDB != null) {
			model.put("message", "Такой пользователь уже зарегистрирован!");
            return "registration";
		}
		
		user.setActive(true);
		user.setRole(Collections.singleton(Role.STUDENT));
		
		userRepos.save(user);
		
		return "redirect:/login";
	}
	
    @GetMapping
    public String main() {
        return "main";
    }
    
    @GetMapping("/greeting")
    public String greeting() {
        return "greeting";
    }
}
