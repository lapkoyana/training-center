package com.example.qualitycontrolsystem.dao;

import java.util.List;

import com.example.qualitycontrolsystem.entity.Answer;
import com.example.qualitycontrolsystem.repos.AnswerRepository;

public class AnswerDAOImpl implements AnswerDAO{

	AnswerRepository answerRepository;
	
	@Override
	public List<Answer> getAllAnswers() {
		return answerRepository.findAll();
	}

	@Override
	public Answer getAnswer(long id) {
		return answerRepository.findById(id).orElseThrow(RuntimeException::new);
	}

	@Override
	public void addAnswer(Answer answer) {
		answerRepository.save(answer);
	}
	
}
