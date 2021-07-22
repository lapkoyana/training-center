package com.qcs.qualitycontrolsystem.service;

import java.util.List;

import com.qcs.qualitycontrolsystem.dto.QuestionDto;
import com.qcs.qualitycontrolsystem.dto.QuestionDtoWithId;
import com.qcs.qualitycontrolsystem.entity.Lesson;

public interface QuestionService {

	public List<QuestionDtoWithId> getAllQuestions();

	public QuestionDtoWithId getQuestion(long id);

	public List<QuestionDtoWithId> getQuestionsByLesson(long lessonId);

	public void addQuestion(long lesson, QuestionDto question);

	public void updateQuestion(long lessonId, QuestionDtoWithId question);

	public void deleteQuestion(long lesson, long id);
}
