package com.qcs.qualitycontrolsystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qcs.qualitycontrolsystem.entity.Lesson;
import com.qcs.qualitycontrolsystem.repos.LessonRepository;

@Service
public class LessonServiceImpl implements LessonService {

	@Autowired
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
	public void saveLesson(Lesson lesson) {
		lessonRepository.save(lesson);
	}

	@Override
	public void deleteLesson(long id) {
		lessonRepository.deleteById(id);
	}
}
