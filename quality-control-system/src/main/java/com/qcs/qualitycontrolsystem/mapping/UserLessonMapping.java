package com.qcs.qualitycontrolsystem.mapping;

import org.springframework.stereotype.Service;

import com.qcs.qualitycontrolsystem.dto.UserLessonDto;
import com.qcs.qualitycontrolsystem.entity.UserLesson;

@Service
public class UserLessonMapping {
	public UserLessonDto mapToUserLessonDto (UserLesson userLesson){
		UserLessonDto userLessonDto = new UserLessonDto();
		userLessonDto.setLessonId(userLesson.getLesson().getId());
		userLessonDto.setUserId(userLesson.getUser().getId());
		userLessonDto.setSignOfCompleteness(userLesson.isSignOfCompleteness());
		return userLessonDto;
	}
}
