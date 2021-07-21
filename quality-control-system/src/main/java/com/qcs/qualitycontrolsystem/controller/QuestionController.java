package com.qcs.qualitycontrolsystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qcs.qualitycontrolsystem.entity.Answer;
import com.qcs.qualitycontrolsystem.entity.Lesson;
import com.qcs.qualitycontrolsystem.entity.Question;
import com.qcs.qualitycontrolsystem.entity.User;
import com.qcs.qualitycontrolsystem.service.AnswerService;
import com.qcs.qualitycontrolsystem.service.QuestionService;

@RestController
@RequestMapping("/lections")
public class QuestionController {

	@Autowired
	private QuestionService questionServise;
	@Autowired
	private AnswerService answerService;

	@GetMapping("/{lesson}/questions")
	public List<Question> getQuestions(@PathVariable Lesson lesson) {
		return questionServise.getQuestionsByLesson(lesson);
	}

	@PostMapping("/{lesson}/questions")
	public Question addQuestion(@PathVariable Lesson lesson, @RequestBody Question question) {
		questionServise.addQuestion(lesson, question);
		return question;
	}

	@PatchMapping("/{lesson}/questions")
	public Question updateQuestion(@PathVariable Lesson lesson, @RequestBody Question question) {
		questionServise.updateQuestion(lesson, question);
		return question;
	}

	@DeleteMapping("/{lesson}/questions/{id}")
	public String deleteQuestion(@PathVariable Lesson lesson, @PathVariable Long id) {
		questionServise.deleteQuestion(lesson, id);
		return "Done!";
	}

	// for answers

	@GetMapping("/{lesson}/user/{user}/answers")
	public List<Answer> getAnswers(@PathVariable Lesson lesson, @PathVariable User user) {
		return answerService.getAnswersByLessonAndUser(lesson, user);
	}
}
