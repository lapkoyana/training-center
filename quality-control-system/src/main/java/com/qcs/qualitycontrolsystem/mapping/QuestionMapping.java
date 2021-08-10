package com.qcs.qualitycontrolsystem.mapping;

import org.springframework.stereotype.Service;

import com.qcs.qualitycontrolsystem.dto.QuestionDto;
import com.qcs.qualitycontrolsystem.dto.QuestionDtoWithId;
import com.qcs.qualitycontrolsystem.entity.Lesson;
import com.qcs.qualitycontrolsystem.entity.Question;

@Service
public class QuestionMapping {

	public QuestionDtoWithId mapToQuestionDto(Question question) {
		QuestionDtoWithId dto = new QuestionDtoWithId();
		dto.setId(question.getId());
		dto.setContent(question.getContent());
		return dto;
	}

	public Question mapToQuestion(QuestionDto dto, Lesson lesson) {
		Question question = new Question();
		question.setContent(dto.getContent());
		question.setLesson(lesson);
		return question;
	}

	public Question mapToQuestion(QuestionDtoWithId dto, Lesson lesson) {
		Question question = new Question();
		question.setId(dto.getId());
		question.setContent(dto.getContent());
		question.setLesson(lesson);
		return question;
	}
}
