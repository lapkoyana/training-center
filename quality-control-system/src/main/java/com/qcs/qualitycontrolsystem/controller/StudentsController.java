package com.qcs.qualitycontrolsystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qcs.qualitycontrolsystem.dto.AnswerDto;
import com.qcs.qualitycontrolsystem.dto.LessonDtoWithIdResp;
import com.qcs.qualitycontrolsystem.dto.QuestionDtoWithId;
import com.qcs.qualitycontrolsystem.dto.UserLessonDto;
import com.qcs.qualitycontrolsystem.entity.User;
import com.qcs.qualitycontrolsystem.service.AnswerService;
import com.qcs.qualitycontrolsystem.service.LessonService;
import com.qcs.qualitycontrolsystem.service.QuestionService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/lessons")
@PreAuthorize("hasAuthority('STUDENT')")
public class StudentsController {

	@Autowired
	private LessonService lessonServise;
	@Autowired
	private QuestionService questionServise;
	@Autowired
	private AnswerService answerService;

	@GetMapping
	public ResponseEntity<?> getLessons() {
		List<LessonDtoWithIdResp> lessonsDto = lessonServise.getAllLessons();
		return ResponseEntity.ok(lessonsDto);
	}

	@GetMapping("/{lessonId}/questions")
	public ResponseEntity<?> getQuestions(@PathVariable long lessonId) {
		List<QuestionDtoWithId> questionsDto = questionServise.getQuestionsByLesson(lessonId);
		return ResponseEntity.ok(questionsDto);
	}

	@PostMapping("/{lessonId}/questions")
	public ResponseEntity<?> addAnswers(
			@PathVariable long lessonId,
			@RequestBody List<AnswerDto> answersDto,
			@AuthenticationPrincipal User user) {
		answerService.addAnswer(answersDto, lessonId, user);
		return ResponseEntity.ok().body(HttpStatus.CREATED);
	}
	
	@GetMapping("/{lessonId}")
	public ResponseEntity<?> getUserLesson(
			@PathVariable long lessonId,
			@AuthenticationPrincipal User user){
		UserLessonDto lessonUserDto = lessonServise.getSignOfCompletenessForUserAndLesson(lessonId, user);
		return ResponseEntity.ok(lessonUserDto);
	}
}