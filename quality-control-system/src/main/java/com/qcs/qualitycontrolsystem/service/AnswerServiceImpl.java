package com.qcs.qualitycontrolsystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qcs.qualitycontrolsystem.entity.Answer;
import com.qcs.qualitycontrolsystem.entity.Lesson;
import com.qcs.qualitycontrolsystem.entity.User;
import com.qcs.qualitycontrolsystem.repos.AnswerRepository;

@Service
public class AnswerServiceImpl implements AnswerService {

	@Autowired
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
	public void addAnswer(List<Answer> answers, Lesson lesson) {
		
		answers.forEach(a -> a.setLesson(lesson));
		
		for(int i = 0; i < answers.size(); i++) {
			Answer a = answers.get(i);
			a.setQuestion(a.getLesson().getQuestions().get(i));
		}
		
		answerRepository.saveAll(answers);
	}

	@Override
	public List<Answer> getAnswersByLessonAndUser(Lesson lesson, User user) {
		return answerRepository.findByUserAndLesson(user, lesson);
	}

}
