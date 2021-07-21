package com.qcs.qualitycontrolsystem.service;

import java.util.List;

import com.qcs.qualitycontrolsystem.dto.LessonDto;

public interface LessonService {

	public List<LessonDto> getAllLessons();

	public LessonDto getLesson(long id);

	public void saveLesson(LessonDto lesson);

	public void deleteLesson(long id);
}
