package com.qcs.qualitycontrolsystem.mapping;

import org.springframework.stereotype.Service;

import com.qcs.qualitycontrolsystem.dto.AnswerDto;
import com.qcs.qualitycontrolsystem.dto.AnswerDtoWithId;
import com.qcs.qualitycontrolsystem.entity.Answer;
import com.qcs.qualitycontrolsystem.entity.Lesson;
import com.qcs.qualitycontrolsystem.entity.Question;

@Service
public class AnswerMapping {

	public AnswerDtoWithId mapToAnswerDto(Answer answer) {
		AnswerDtoWithId dto = new AnswerDtoWithId();
		dto.setId(answer.getId());
		dto.setDateOfReply(answer.getDateOfReply());
		dto.setContent(answer.getContent());
		dto.setQuestionId(answer.getQuestion().getId());
		// don't forget about the user
		return dto;
	}

	public Answer mapToAnswer(AnswerDto answerDto, Lesson lesson, Question question) {
		Answer answer = new Answer();
		answer.setDateOfReply(answerDto.getDateOfReply());
		answer.setContent(answerDto.getContent());
		answer.setLesson(lesson);
		answer.setQuestion(question);
		// don't forget about the user
		return answer;
	}
}
