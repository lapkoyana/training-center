package com.qcs.qualitycontrolsystem.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qcs.qualitycontrolsystem.entity.Role;
import com.qcs.qualitycontrolsystem.entity.User;
import com.qcs.qualitycontrolsystem.jwt.JwtUtils;
import com.qcs.qualitycontrolsystem.payload.request.LoginRequest;
import com.qcs.qualitycontrolsystem.payload.request.SignupRequest;
import com.qcs.qualitycontrolsystem.payload.response.JwtResponse;
import com.qcs.qualitycontrolsystem.payload.response.MessageResponse;
import com.qcs.qualitycontrolsystem.service.UserService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/auth")
public class AuthController {
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserService userService;

	@Autowired
	JwtUtils jwtUtils;

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);

		User user = (User) authentication.getPrincipal();

		List<String> roles = user.getAuthorities().stream().map(item -> item.getAuthority())
				.collect(Collectors.toList());

		return ResponseEntity.ok(new JwtResponse(jwt, user.getId(), user.getUsername(), roles));
	}

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@RequestBody SignupRequest signupRequest) {

		if (userService.existsByUsername(signupRequest.getUsername())) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: User is already exist!"));
		}

		User user = new User(signupRequest.getUsername(), signupRequest.getPassword());

		Set<String> roleStrings = signupRequest.getRole();
		Set<Role> roles = new HashSet<>();

		if (roleStrings == null) {
			roles.add(Role.STUDENT);
		} else {
			roleStrings.forEach(role -> {
				switch (role) {
				case "STUDENT":
					roles.add(Role.STUDENT);

					break;
				case "LECTURER":
					roles.add(Role.LECTURER);

					break;
				default:
					roles.add(Role.STUDENT);
				}
			});
		}
		user.setRole(roles);

		userService.addUser(user);

		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}
}