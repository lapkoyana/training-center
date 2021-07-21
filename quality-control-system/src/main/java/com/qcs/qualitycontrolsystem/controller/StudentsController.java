package com.qcs.qualitycontrolsystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qcs.qualitycontrolsystem.entity.Answer;
import com.qcs.qualitycontrolsystem.entity.Lesson;
import com.qcs.qualitycontrolsystem.entity.Question;
import com.qcs.qualitycontrolsystem.service.AnswerService;
import com.qcs.qualitycontrolsystem.service.LessonService;
import com.qcs.qualitycontrolsystem.service.QuestionService;

@RestController
@RequestMapping("/lessons")
public class StudentsController {

	@Autowired
	private LessonService lessonServise;
	@Autowired
	private QuestionService questionServise;
	@Autowired
	private AnswerService answerService;

	@GetMapping
	public List<Lesson> getLessons() {
		return lessonServise.getAllLessons();
	}

	@GetMapping("/{lesson}/questions")
	public List<Question> getQuestions(@PathVariable Lesson lesson) {
		return questionServise.getQuestionsByLesson(lesson);
	}

	@PostMapping("/{lesson}/questions")
	public List<Answer> addAnswers(@PathVariable Lesson lesson, @RequestBody List<Answer> answers) {
		answerService.addAnswer(answers, lesson);
		return answers;
	}

}
