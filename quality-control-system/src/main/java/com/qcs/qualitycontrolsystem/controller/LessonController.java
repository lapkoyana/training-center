package com.qcs.qualitycontrolsystem.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qcs.qualitycontrolsystem.dto.LessonDto;
import com.qcs.qualitycontrolsystem.dto.LessonDtoWithId;
import com.qcs.qualitycontrolsystem.service.LessonService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/lections")
public class LessonController {

	@Autowired
	private LessonService lessonServise;

	@Value("${upload.path}")
	private String uploadPath;

	@GetMapping
	public ResponseEntity<?> getLessons() {
		List<LessonDtoWithId> lessonsDto = lessonServise.getAllLessons();
		return ResponseEntity.ok(lessonsDto);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getLesson(@PathVariable Long id) {
		LessonDtoWithId lessonDto = lessonServise.getLesson(id);
		return ResponseEntity.ok(lessonDto);
	}

	@PostMapping
	public ResponseEntity<?> addLesson(@RequestBody LessonDto lessonDto) {
		lessonServise.addLesson(lessonDto);
		return ResponseEntity.ok().body(HttpStatus.CREATED);
	}

	@PutMapping
	public ResponseEntity<?> updateLesson(@RequestBody LessonDtoWithId lessonDto) {
		lessonServise.updateLesson(lessonDto);
		return ResponseEntity.ok().body(HttpStatus.OK);
	}

	@DeleteMapping("{id}")
	public ResponseEntity<?> deleteLesson(@PathVariable Long id) {
		lessonServise.deleteLesson(id);
		return ResponseEntity.ok().body(HttpStatus.OK);
	}
}
