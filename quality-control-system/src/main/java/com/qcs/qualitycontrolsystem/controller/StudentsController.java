package com.qcs.qualitycontrolsystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CrossOrigin;
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
import com.qcs.qualitycontrolsystem.service.UserDetailsImpl;
import com.qcs.qualitycontrolsystem.service.UserLessonService;
import com.qcs.qualitycontrolsystem.service.UserService;

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
	@Autowired
	private UserLessonService userLessonService;
	@Autowired
	private UserService userService;

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
			@AuthenticationPrincipal UserDetailsImpl userDetailsImpl) {
		User user = userService.findById(userDetailsImpl.getId());
		answerService.addAnswer(answersDto, lessonId, user);
		return ResponseEntity.ok().body(HttpStatus.CREATED);
	}
	
	@GetMapping("/completeness")
	public ResponseEntity<?> getUserLesson(
			@AuthenticationPrincipal UserDetailsImpl userDetailsImpl){
		User user = userService.findById(userDetailsImpl.getId());
		List<UserLessonDto> lessonUserDtos = userLessonService.getByUser(user);
		return ResponseEntity.ok(lessonUserDtos);
	}
	
	@GetMapping("/files/{filename}")
	public ResponseEntity<?> getFileList(@PathVariable String filename) {
		byte[] file = lessonServise.load(filename);
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment").body(file);
	}
}