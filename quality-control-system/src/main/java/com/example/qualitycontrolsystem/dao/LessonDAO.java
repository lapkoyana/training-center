package com.example.qualitycontrolsystem.dao;

import java.util.List;

import com.example.qualitycontrolsystem.entity.Lesson;
import com.example.qualitycontrolsystem.entity.Question;

public interface LessonDAO {
	
	public List<Lesson> getAllLessons();
	
	public Lesson getLesson(long id);
	
	public void addLesson(Lesson lesson);
		
	public void updateLesson(Lesson lesson, long id);
	
	public void deleteLesson(long id);

	void updateLessonsQuestions(long id, List<Question> questions);
}
