package com.example.qualitycontrolsystem.service;

import java.util.List;

import com.example.qualitycontrolsystem.entity.Lesson;
import com.example.qualitycontrolsystem.entity.Question;

//лекции можно:
//получить все лекции
//получить одну
//добавить
//изменить
//изменить - добавить вопросы
//удалить

public interface LessonService {
	
	public List<Lesson> getAllLessons();
	
	public Lesson getLesson(long id);
	
	public void addLesson(Lesson lesson);
	
	public void updateLesson(Lesson lesson, long id);
	
	public void updateLessonsQuestions(long id, List<Question> questions);
	
	public void deleteLesson(long id);
}
