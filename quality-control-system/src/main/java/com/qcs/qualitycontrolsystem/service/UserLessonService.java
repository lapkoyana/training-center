package com.qcs.qualitycontrolsystem.service;

import java.util.List;

import com.qcs.qualitycontrolsystem.dto.UserLessonDto;
import com.qcs.qualitycontrolsystem.entity.User;

public interface UserLessonService {
	
	public UserLessonDto getByUserAndLesson(User user, long lessonId);
	
	public List<UserLessonDto> getByUser(User user);
}