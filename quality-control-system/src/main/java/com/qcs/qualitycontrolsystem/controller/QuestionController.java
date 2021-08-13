package com.qcs.qualitycontrolsystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qcs.qualitycontrolsystem.dto.AnswerDtoWithId;
import com.qcs.qualitycontrolsystem.dto.QuestionDto;
import com.qcs.qualitycontrolsystem.dto.QuestionDtoWithId;
import com.qcs.qualitycontrolsystem.entity.User;
import com.qcs.qualitycontrolsystem.service.AnswerService;
import com.qcs.qualitycontrolsystem.service.QuestionService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/lections")
@PreAuthorize("hasAuthority('LECTURER')")
public class QuestionController {

	@Autowired
	private QuestionService questionServise;
	@Autowired
	private AnswerService answerService;

	@GetMapping("/{lessonId}/questions")
	public ResponseEntity<?> getQuestions(@PathVariable long lessonId) {
		List<QuestionDtoWithId> questionsDto = questionServise.getQuestionsByLesson(lessonId);
		return ResponseEntity.ok(questionsDto);
	}

	@PostMapping("/{lessonId}/questions")
	public ResponseEntity<?> addQuestion(@PathVariable long lessonId, @RequestBody QuestionDto questionDto) {
		questionServise.addQuestion(lessonId, questionDto);
		return ResponseEntity.ok().body(HttpStatus.CREATED);
	}

	@PutMapping("/{lessonId}/questions")
	public ResponseEntity<?> updateQuestion(@PathVariable long lessonId, @RequestBody QuestionDtoWithId questionDto) {
		questionServise.updateQuestion(lessonId, questionDto);
		return ResponseEntity.ok().body(HttpStatus.OK);
	}

	@DeleteMapping("/{lessonId}/questions/{questionId}")
	public ResponseEntity<?> deleteQuestion(@PathVariable long lessonId, @PathVariable long questionId) {
		questionServise.deleteQuestion(lessonId, questionId);
		return ResponseEntity.ok().body(HttpStatus.OK);
	}

	@GetMapping("/{lessonId}/user/{user}/answers")
	public ResponseEntity<?> getAnswers(@PathVariable long lessonId, @PathVariable User user) {
		List<AnswerDtoWithId> answersDto = answerService.getAnswersByLessonAndUser(lessonId, user);
		return ResponseEntity.ok(answersDto);
	}
}
