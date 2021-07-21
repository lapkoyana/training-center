package com.qcs.qualitycontrolsystem.service;

import java.util.List;

import com.qcs.qualitycontrolsystem.entity.Lesson;
import com.qcs.qualitycontrolsystem.entity.Question;

public interface QuestionService {

	public List<Question> getAllQuestions();

	public Question getQuestion(long id);

	public List<Question> getQuestionsByLesson(Lesson lesson);

	public void addQuestion(Lesson lesson, Question question);

	public void updateQuestion(Lesson lesson, Question question);

	public void deleteQuestion(Lesson lesson, long id);
}
