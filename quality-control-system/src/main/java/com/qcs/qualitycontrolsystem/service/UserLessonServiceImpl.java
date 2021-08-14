package com.qcs.qualitycontrolsystem.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qcs.qualitycontrolsystem.dto.UserLessonDto;
import com.qcs.qualitycontrolsystem.entity.Lesson;
import com.qcs.qualitycontrolsystem.entity.User;
import com.qcs.qualitycontrolsystem.entity.UserLesson;
import com.qcs.qualitycontrolsystem.mapping.UserLessonMapping;
import com.qcs.qualitycontrolsystem.repos.LessonRepository;
import com.qcs.qualitycontrolsystem.repos.UserLessonRepository;

@Service
public class UserLessonServiceImpl implements UserLessonService{
	@Autowired
	private UserLessonRepository userLessonRepository;
	@Autowired
	private LessonRepository lessonRepository;
	@Autowired
	private UserLessonMapping userLessonMapping;
	
	@Override
	public UserLessonDto getByUserAndLesson(User user, long lessonId) {
		Lesson lesson = lessonRepository.findById(lessonId).orElse(null);
		return userLessonMapping.mapToUserLessonDto(userLessonRepository.findByUserAndLesson(user, lesson));
	}
	
	@Override
	public List<UserLessonDto> getByUser(User user) {
		List<UserLesson> UserLessonListByUser = userLessonRepository.findByUser(user);
		List<UserLessonDto> UserLessonDtoListByUser = UserLessonListByUser.stream()
			.map(ul -> userLessonMapping.mapToUserLessonDto(ul))
			.collect(Collectors.toList());
		return UserLessonDtoListByUser;
	}
}
