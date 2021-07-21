package com.qcs.qualitycontrolsystem.service;

import java.util.List;

import com.qcs.qualitycontrolsystem.entity.Lesson;
import com.qcs.qualitycontrolsystem.entity.Question;

public interface LessonService {

	public List<Lesson> getAllLessons();

	public Lesson getLesson(long id);

	public void saveLesson(Lesson lesson);

	public void deleteLesson(long id);
}
