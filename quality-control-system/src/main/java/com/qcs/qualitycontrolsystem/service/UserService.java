package com.qcs.qualitycontrolsystem.service;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.qcs.qualitycontrolsystem.entity.Role;
import com.qcs.qualitycontrolsystem.entity.User;
import com.qcs.qualitycontrolsystem.repos.UserRepository;

@Service
public class UserService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username);
    }

	public boolean addUser(User user) {
		User userFromDB = userRepository.findByUsername(user.getUsername());

		if (userFromDB != null) {
			return false;
		}

		user.setActive(true);
		user.setRole(Collections.singleton(Role.STUDENT));

		userRepository.save(user);

		return true;
	}

	public User findById(long id) {
		return userRepository.findById(id).orElse(null);
	}
}
