package com.qcs.qualitycontrolsystem.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qcs.qualitycontrolsystem.dto.LessonDto;
import com.qcs.qualitycontrolsystem.entity.Lesson;
import com.qcs.qualitycontrolsystem.mapping.LessonMapping;
import com.qcs.qualitycontrolsystem.repos.LessonRepository;

@Service
public class LessonServiceImpl implements LessonService {

	@Autowired
	private LessonRepository lessonRepository;
	@Autowired
	private LessonMapping lessonMapping;

	@Override
	public List<LessonDto> getAllLessons() {
		return lessonRepository.findAll().stream()
				.map(lessonMapping::mapToLessonDto)
				.collect(Collectors.toList());
	}

	@Override
	public LessonDto getLesson(long id) {
		return lessonMapping.mapToLessonDto(
				lessonRepository.findById(id).orElse(null));//пусть лучше исключение выбрасывает
	}

	@Override
	public void saveLesson(LessonDto lesson) {
		lessonRepository.save(lessonMapping.mapToLesson(lesson));
	}

	@Override
	public void deleteLesson(long id) {
		lessonRepository.deleteById(id);
	}
}
