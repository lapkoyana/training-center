package com.example.qualitycontrolsystem.service;

import java.util.List;

import com.example.qualitycontrolsystem.dao.AnswerDAO;
import com.example.qualitycontrolsystem.entity.Answer;

public class AnswerServiceImpl implements AnswerService{

	AnswerDAO answerDAO;
	
	@Override
	public List<Answer> getAllAnswers() {
		return answerDAO.getAllAnswers();
	}

	@Override
	public Answer getAnswer(long id) {
		return answerDAO.getAnswer(id);
	}

	@Override
	public void addAnswer(Answer answer) {
		answerDAO.addAnswer(answer);
	}

}
