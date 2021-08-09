package com.qcs.qualitycontrolsystem.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qcs.qualitycontrolsystem.dto.LessonDto;
import com.qcs.qualitycontrolsystem.dto.LessonDtoWithId;
import com.qcs.qualitycontrolsystem.dto.LessonDtoWithIdResp;
import com.qcs.qualitycontrolsystem.service.LessonService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/lections")
public class LessonController {

	@Autowired
	private LessonService lessonServise;

	@GetMapping
	public ResponseEntity<?> getLessons() {
		List<LessonDtoWithIdResp> lessonsDto = lessonServise.getAllLessons();
		return ResponseEntity.ok(lessonsDto);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getLesson(@PathVariable Long id) {
		LessonDtoWithIdResp lessonDto = lessonServise.getLesson(id);
		return ResponseEntity.ok(lessonDto);
	}

	@PostMapping
	public ResponseEntity<?> addLesson(
			@RequestParam("lesson") String lessonDtoString,
			@RequestParam(value = "file", required=false) MultipartFile file) throws JsonMappingException, JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		LessonDto lessonDto = objectMapper.readValue(lessonDtoString, LessonDto.class);
		lessonServise.addLesson(lessonDto, file);
		return ResponseEntity.ok().body(HttpStatus.CREATED);
	}

	@PutMapping
	public ResponseEntity<?> updateLesson(
			@RequestParam("lesson") String lessonDtoString,
			@RequestParam(value = "file", required=false) MultipartFile file) throws JsonMappingException, JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		LessonDtoWithId lessonDto = objectMapper.readValue(lessonDtoString, LessonDtoWithId.class);
		lessonServise.updateLesson(lessonDto, file);
		return ResponseEntity.ok().body(HttpStatus.OK);
	}

	@DeleteMapping("{id}")
	public ResponseEntity<?> deleteLesson(@PathVariable Long id) {
		lessonServise.deleteLesson(id);
		return ResponseEntity.ok().body(HttpStatus.OK);
	}
}
