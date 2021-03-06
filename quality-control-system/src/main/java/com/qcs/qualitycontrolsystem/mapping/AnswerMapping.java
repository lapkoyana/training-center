package com.qcs.qualitycontrolsystem.mapping;


import java.sql.Date;

import org.springframework.stereotype.Service;

import com.qcs.qualitycontrolsystem.dto.AnswerDto;
import com.qcs.qualitycontrolsystem.dto.AnswerDtoWithId;
import com.qcs.qualitycontrolsystem.entity.Answer;
import com.qcs.qualitycontrolsystem.entity.Lesson;
import com.qcs.qualitycontrolsystem.entity.Question;
import com.qcs.qualitycontrolsystem.entity.User;

@Service
public class AnswerMapping {

	public AnswerDtoWithId mapToAnswerDto(Answer answer) {
		AnswerDtoWithId dto = new AnswerDtoWithId();
		dto.setId(answer.getId());
		dto.setDateOfReply(answer.getDateOfReply());
		dto.setContent(answer.getContent());
		dto.setQuestion(answer.getQuestion().getContent());
		dto.setLesson(answer.getLesson().getId());
		dto.setUser(answer.getUser().getId());
		return dto;
	}

	public Answer mapToAnswer(AnswerDto answerDto, Lesson lesson, Question question, User user) {
		Answer answer = new Answer();
		answer.setDateOfReply( new Date(System.currentTimeMillis()) );
		answer.setContent(answerDto.getContent());
		answer.setLesson(lesson);
		answer.setQuestion(question);
		answer.setUser(user);
		return answer;
	}
}
