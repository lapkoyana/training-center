package com.qcs.qualitycontrolsystem.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qcs.qualitycontrolsystem.dto.UserDto;
import com.qcs.qualitycontrolsystem.entity.Lesson;
import com.qcs.qualitycontrolsystem.entity.Role;
import com.qcs.qualitycontrolsystem.entity.User;
import com.qcs.qualitycontrolsystem.entity.UserLesson;
import com.qcs.qualitycontrolsystem.mapping.UserMapping;
import com.qcs.qualitycontrolsystem.repos.LessonRepository;
import com.qcs.qualitycontrolsystem.repos.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private UserMapping userMapping;
	@Autowired
	private LessonRepository lessonRepository;
	
	public boolean addUser(User user) {
		List<Lesson> allLessons = lessonRepository.findAll();
		Set<UserLesson> userLessonsForThisUser = new HashSet<>();
		allLessons.forEach(lesson -> {
			UserLesson userLesson = new UserLesson(user, lesson);
			userLessonsForThisUser.add(userLesson);
		});
		user.setUserLesson(userLessonsForThisUser);
		
		userRepository.save(user);
		return true;
	}

	public User findById(long id) {
		return userRepository.findById(id).orElse(null);
	}

	public Boolean existsByUsername(String username) {
		return userRepository.existsByUsername(username);
	}
	
	public List<User> getAllUsers(){
		return userRepository.findAll();
	}
	
	public List<UserDto> getAllStudents() {
		List<User> allStudents = getAllUsers().stream()
				.filter(user -> user.getRole().iterator().next().equals(Role.STUDENT)).collect(Collectors.toList());
		
		List<UserDto> userDtos = allStudents.stream().map(u -> userMapping.mapToUserDto(u)).collect(Collectors.toList());
		return userDtos;
	}
}
