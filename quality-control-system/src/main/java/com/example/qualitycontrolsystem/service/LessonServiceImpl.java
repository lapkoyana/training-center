package com.example.qualitycontrolsystem.service;

import java.util.List;

import com.example.qualitycontrolsystem.dao.LessonDAO;
import com.example.qualitycontrolsystem.entity.Lesson;
import com.example.qualitycontrolsystem.entity.Question;

public class LessonServiceImpl implements LessonService{

	LessonDAO lessonDAO;
	
	@Override
	public List<Lesson> getAllLessons() {
		return lessonDAO.getAllLessons();
	}

	@Override
	public Lesson getLesson(long id) {
		return lessonDAO.getLesson(id);
	}

	@Override
	public void addLesson(Lesson lesson) {
		lessonDAO.addLesson(lesson);
	}

	@Override
	public void updateLesson(Lesson lesson, long id) {
		lessonDAO.updateLesson(lesson, id);
	}

	@Override
	public void updateLessonsQuestions(long id, List<Question> questions) {
		lessonDAO.updateLessonsQuestions(id, questions);
	}

	@Override
	public void deleteLesson(long id) {
		lessonDAO.deleteLesson(id);
	}

}
