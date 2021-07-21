package com.qcs.qualitycontrolsystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qcs.qualitycontrolsystem.entity.Lesson;
import com.qcs.qualitycontrolsystem.entity.Question;
import com.qcs.qualitycontrolsystem.repos.QuestionRepository;

@Service
public class QuestionServiceImpl implements QuestionService {

	@Autowired
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
	public void addQuestion(Lesson lesson, Question question) {
		lesson.getQuestions().add(question);
		question.setLesson(lesson);
		questionRepository.save(question);
	}

	@Override
	public void updateQuestion(Lesson lesson, Question question) {
		question.setLesson(lesson);
		questionRepository.save(question);
	}

	@Override
	public void deleteQuestion(Lesson lesson, long id) {
		Question question = questionRepository.getById(id);
		lesson.getQuestions().remove(question);
		questionRepository.deleteById(id);
		;
	}

	@Override
	public List<Question> getQuestionsByLesson(Lesson lesson) {
		return questionRepository.findByLesson(lesson);
	}
}
