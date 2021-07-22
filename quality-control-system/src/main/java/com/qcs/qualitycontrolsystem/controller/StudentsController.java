package com.qcs.qualitycontrolsystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qcs.qualitycontrolsystem.dto.AnswerDto;
import com.qcs.qualitycontrolsystem.dto.LessonDtoWithId;
import com.qcs.qualitycontrolsystem.dto.QuestionDtoWithId;
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
	public ResponseEntity<?> getLessons() {
		List<LessonDtoWithId> lessonsDto = lessonServise.getAllLessons();
		return ResponseEntity.ok(lessonsDto);
	}

	@GetMapping("/{lessonId}/questions")
	public ResponseEntity<?> getQuestions(@PathVariable long lessonId) {
		List<QuestionDtoWithId> questionsDto = questionServise.getQuestionsByLesson(lessonId);
		return ResponseEntity.ok(questionsDto);
	}

	// don't forget about the user
	@PostMapping("/{lessonId}/questions")
	public ResponseEntity<?> addAnswers(@PathVariable long lessonId, @RequestBody List<AnswerDto> answersDto) {
		answerService.addAnswer(answersDto, lessonId);
		return ResponseEntity.ok().body(HttpStatus.CREATED);
	}
}
