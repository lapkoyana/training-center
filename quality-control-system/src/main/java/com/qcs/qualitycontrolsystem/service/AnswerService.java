package com.qcs.qualitycontrolsystem.service;

import java.util.List;

import com.qcs.qualitycontrolsystem.dto.AnswerDto;
import com.qcs.qualitycontrolsystem.dto.AnswerDtoWithId;
import com.qcs.qualitycontrolsystem.entity.User;

public interface AnswerService {

	public List<AnswerDtoWithId> getAllAnswers();

	public AnswerDtoWithId getAnswer(long id);

	public void addAnswer(List<AnswerDto> answersDto, long lessonId, User user);

	public List<AnswerDtoWithId> getAnswersByLessonAndUser(long lesson, long user);

}
