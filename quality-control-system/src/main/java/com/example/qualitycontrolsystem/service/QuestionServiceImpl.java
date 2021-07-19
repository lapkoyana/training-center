package com.example.qualitycontrolsystem.service;

import java.util.List;

import com.example.qualitycontrolsystem.dao.QuestionDAO;
import com.example.qualitycontrolsystem.entity.Question;

public class QuestionServiceImpl implements QuestionService{
	
	QuestionDAO questionDAO;
	
	public List<Question> getAllQuestions(){
		return questionDAO.getAllQuestions();
	}
	
	public Question getQuestion(long id) {
		return questionDAO.getQuestion(id);
	}
	
	public void addQuestion(Question question) {
		questionDAO.addQuestion(question);
	}
	
	public void updateQuestion(Question question, long id) {
		questionDAO.updateQuestion(question, id);
	}
	
	public void deleteQuestion(long id) {
		questionDAO.deleteQuestion(id);
	}
}
