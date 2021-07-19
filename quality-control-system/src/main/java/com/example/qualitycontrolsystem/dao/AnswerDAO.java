package com.example.qualitycontrolsystem.dao;

import java.util.List;

import com.example.qualitycontrolsystem.entity.Answer;

public interface AnswerDAO {
	
	public List<Answer> getAllAnswers();
	
	public Answer getAnswer(long id);
	
	public void addAnswer(Answer answer);
}
