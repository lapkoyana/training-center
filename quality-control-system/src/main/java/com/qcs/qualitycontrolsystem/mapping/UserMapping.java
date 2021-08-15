package com.qcs.qualitycontrolsystem.mapping;

import org.springframework.stereotype.Service;

import com.qcs.qualitycontrolsystem.dto.UserDto;
import com.qcs.qualitycontrolsystem.entity.User;

@Service
public class UserMapping {
	public UserDto mapToUserDto (User user) {
		UserDto userDto = new UserDto();
		userDto.setId(user.getId());
		userDto.setUsername(user.getUsername());
		return userDto;
	}
}