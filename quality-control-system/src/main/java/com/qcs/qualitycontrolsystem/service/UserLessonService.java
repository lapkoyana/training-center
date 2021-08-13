package com.qcs.qualitycontrolsystem.service;

import java.util.List;

import com.qcs.qualitycontrolsystem.entity.Lesson;
import com.qcs.qualitycontrolsystem.entity.User;
import com.qcs.qualitycontrolsystem.entity.UserLesson;

public interface UserLessonService {
	public UserLesson getByUserAndLesson(User user, long lessonId);
	
	public List<UserLesson> getByUser(User user);
	
	public List<UserLesson> getByLesson(Lesson lesson);
}
