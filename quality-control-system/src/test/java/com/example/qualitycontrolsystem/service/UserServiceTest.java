package com.example.qualitycontrolsystem.service;

import java.util.Collections;
import java.util.Optional;

import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.example.qualitycontrolsystem.entity.Role;
import com.example.qualitycontrolsystem.entity.User;
import com.example.qualitycontrolsystem.repos.UserRepository;

@SpringBootTest
public class UserServiceTest {
	@Autowired
	private UserService userService;
	
	@MockBean
	private UserRepository userRepository;
	
	@Test
	public void testAddUser() {
		User user = new User();
		
		boolean isUserCreated = userService.addUser(user);
		
		Assertions.assertTrue(isUserCreated);
		Assertions.assertTrue(CoreMatchers.is(user.getRole()).matches(Collections.singleton(Role.STUDENT)));
		
		Mockito.verify(userRepository, Mockito.times(1)).save(user);
	}
	
	@Test
	public void addUserFailTest(){
		User user = new User();
		
		user.setUsername("User");
		
		Mockito.doReturn(new User())
			.when(userRepository)
			.findByUsername("User");
		
		boolean isUserCreated = userService.addUser(user);
		
		Assertions.assertFalse(isUserCreated);
		
		Mockito.verify(userRepository, Mockito.times(0)).save(ArgumentMatchers.any(User.class));
	}
	
	@Test
	public void testFindById() {
		User user = new User();
		
		user.setId(1L);
		user.setActive(true);
		user.setPassword("1");
		user.setUsername("User");
		
		Mockito.when(userRepository.findById(1L)).thenReturn(Optional.of(user));
		
		User result = userService.findById(1L);
		
		Assertions.assertEquals(1L, result.getId());
		Assertions.assertEquals(true, result.isActive());
		Assertions.assertEquals("1", result.getPassword());
		Assertions.assertEquals("User", result.getUsername());
	}
}
