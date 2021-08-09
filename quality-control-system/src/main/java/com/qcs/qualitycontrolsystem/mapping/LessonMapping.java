package com.qcs.qualitycontrolsystem.mapping;

import java.text.ParseException;

import org.springframework.stereotype.Service;

import com.qcs.qualitycontrolsystem.dto.LessonDto;
import com.qcs.qualitycontrolsystem.dto.LessonDtoWithId;
import com.qcs.qualitycontrolsystem.dto.LessonDtoWithIdResp;
import com.qcs.qualitycontrolsystem.entity.Lesson;

@Service
public class LessonMapping {
	
	public LessonDtoWithIdResp mapToLessonDto(Lesson lesson) {
		LessonDtoWithIdResp dto = new LessonDtoWithIdResp();
		dto.setId(lesson.getId());
		dto.setTopic(lesson.getTopic());
		dto.setDateOfClass(lesson.getDateOfClass());		
		dto.setLectureFile(lesson.getLectureFile());
		dto.setSignOfCompleteness(lesson.isSignOfCompleteness());
		return dto;
	}

	public Lesson mapToLesson(LessonDto dto) throws ParseException {
		Lesson lesson = new Lesson();
		lesson.setTopic(dto.getTopic());
		lesson.setDateOfClass(dto.getDateOfClass());
		lesson.setSignOfCompleteness(dto.isSignOfCompleteness());
		return lesson;
	}

	public Lesson mapToLesson(LessonDtoWithId dto) throws ParseException {
		Lesson lesson = new Lesson();
		lesson.setId(dto.getId());
		lesson.setTopic(dto.getTopic());
		lesson.setDateOfClass(dto.getDateOfClass());
		lesson.setSignOfCompleteness(dto.isSignOfCompleteness());
		return lesson;
	}
}
