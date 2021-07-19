package com.example.qualitycontrolsystem.service;

import java.util.List;

import com.example.qualitycontrolsystem.entity.Answer;

//ответы можно:
//получить все
//получить один
//добавлять
//хз удалять надо что ли

public interface AnswerService {
	
	public List<Answer> getAllAnswers();
	
	public Answer getAnswer(long id);
	
	public void addAnswer(Answer answer);
	
	
}
