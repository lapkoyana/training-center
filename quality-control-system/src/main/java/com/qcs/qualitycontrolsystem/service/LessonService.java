package com.qcs.qualitycontrolsystem.service;

import java.util.List;

import com.qcs.qualitycontrolsystem.dto.LessonDto;
import com.qcs.qualitycontrolsystem.dto.LessonDtoWithId;

public interface LessonService {

	public List<LessonDtoWithId> getAllLessons();

	public LessonDtoWithId getLesson(long id);

	public void addLesson(LessonDto lesson);

	public void updateLesson(LessonDtoWithId lesson);

	public void deleteLesson(long id);
}
