package com.example.qualitycontrolsystem.dao;

import java.util.List;

import com.example.qualitycontrolsystem.entity.Question;

public interface QuestionDAO {

	public List<Question> getAllQuestions();
	
	public Question getQuestion(long id);
	
	public void addQuestion(Question question);
	
	public void updateQuestion(Question question, long id);
	
	public void deleteQuestion(long id);
}
