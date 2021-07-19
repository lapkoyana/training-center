package com.example.qualitycontrolsystem.dao;

import java.util.List;

import com.example.qualitycontrolsystem.entity.Lesson;
import com.example.qualitycontrolsystem.entity.Question;
import com.example.qualitycontrolsystem.repos.LessonRepository;

public class LessonDAOImpl implements LessonDAO{
	
	LessonRepository lessonRepository;

	@Override
	public List<Lesson> getAllLessons() {
		return lessonRepository.findAll();
	}

	@Override
	public Lesson getLesson(long id) {
		return lessonRepository.findById(id).orElseThrow(RuntimeException::new);
	}

	@Override
	public void addLesson(Lesson lesson) {
		lessonRepository.save(lesson);
	}

	@Override
	public void updateLesson(Lesson lesson, long id) {
		Lesson currentLesson = lessonRepository.findById(id).orElseThrow(RuntimeException::new);
		currentLesson.setTopic(lesson.getTopic());
		currentLesson.setDateOfClass(lesson.getDateOfClass());
		currentLesson.setSignOfCompleteness(lesson.isSignOfCompleteness());
		currentLesson.setLectureFile(lesson.getLectureFile());
		lessonRepository.save(currentLesson);//тут не save наверное
	}

	@Override
	public void updateLessonsQuestions(long id, List<Question> questions) {
		Lesson currentLesson = lessonRepository.findById(id).orElseThrow(RuntimeException::new);
		currentLesson.setQuestions(questions);
		lessonRepository.save(currentLesson);//тут не save наверное
	}

	@Override
	public void deleteLesson(long id) {
		lessonRepository.deleteById(id);
	}

}
