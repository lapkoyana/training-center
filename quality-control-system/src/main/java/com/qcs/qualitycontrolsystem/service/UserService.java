package com.qcs.qualitycontrolsystem.service;

import java.util.List;

import com.qcs.qualitycontrolsystem.dto.UserDto;
import com.qcs.qualitycontrolsystem.entity.User;

public interface UserService {

	public boolean addUser(User user);

	public User findById(long id);

	public Boolean existsByUsername(String username);
	
	public List<User> getAllUsers();
	
	public List<UserDto> getAllStudents();
}
