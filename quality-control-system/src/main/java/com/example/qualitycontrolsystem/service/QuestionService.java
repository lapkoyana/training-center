package com.example.qualitycontrolsystem.service;

import java.util.List;

import com.example.qualitycontrolsystem.entity.Question;

//вопросы можно:
//получить все
//получить один
//добавить
//редактировать
//удалить

public interface QuestionService {
	
	public List<Question> getAllQuestions();
	
	public Question getQuestion(long id);
	
	public void addQuestion(Question question);
	
	public void updateQuestion(Question question, long id);
	
	public void deleteQuestion(long id);
}
