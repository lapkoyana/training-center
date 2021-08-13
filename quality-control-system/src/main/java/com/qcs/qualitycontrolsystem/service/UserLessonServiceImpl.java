package com.qcs.qualitycontrolsystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qcs.qualitycontrolsystem.entity.Lesson;
import com.qcs.qualitycontrolsystem.entity.User;
import com.qcs.qualitycontrolsystem.entity.UserLesson;
import com.qcs.qualitycontrolsystem.repos.LessonRepository;
import com.qcs.qualitycontrolsystem.repos.UserLessonRepository;

@Service
public class UserLessonServiceImpl implements UserLessonService{
	@Autowired
	private UserLessonRepository userLessonRepository;
	@Autowired
	private LessonRepository lessonRepository;

	@Override
	public UserLesson getByUserAndLesson(User user, long lessonId) {
		Lesson lesson = lessonRepository.findById(lessonId).orElse(null);
		return userLessonRepository.findByUserAndLesson(user, lesson);
	}

	@Override
	public List<UserLesson> getByUser(User user) {
		return userLessonRepository.findByUser(user);
	}

	@Override
	public List<UserLesson> getByLesson(Lesson lesson) {
		return userLessonRepository.findByLesson(lesson);

	}

}
