package com.example.qualitycontrolsystem.dao;

import java.util.List;

import com.example.qualitycontrolsystem.entity.Question;
import com.example.qualitycontrolsystem.repos.QuestionRepository;

public class QuestionDAOImpl implements QuestionDAO{
	
	QuestionRepository questionRepository;

	@Override
	public List<Question> getAllQuestions() {
		return questionRepository.findAll();
	}

	@Override
	public Question getQuestion(long id) {
		return questionRepository.findById(id).orElseThrow(RuntimeException::new);
	}

	@Override
	public void addQuestion(Question question) {
		questionRepository.save(question);
	}

	@Override
	public void updateQuestion(Question question, long id) {
		Question currentQuestion = questionRepository.findById(id).orElseThrow(RuntimeException::new);
		currentQuestion.setContent(question.getContent());
		currentQuestion.setLesson(question.getLesson());
		questionRepository.save(currentQuestion);//тут не save наверное
	}

	@Override
	public void deleteQuestion(long id) {
		questionRepository.deleteById(id);;
	}

}
