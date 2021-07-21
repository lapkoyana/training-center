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
import com.qcs.qualitycontrolsystem.entity.Lesson;
import com.qcs.qualitycontrolsystem.service.LessonService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

@RestController
@RequestMapping("/lections")
public class LessonController {

	@Autowired
	private LessonService lessonServise;

	@Value("${upload.path}")
	private String uploadPath;

	@GetMapping
	public List<Lesson> getLessons() {
		lessonServise.getAllLessons();
		return null;
	}

	@GetMapping("/{id}")
	public void getLesson(@PathVariable Long id) {
		lessonServise.getLesson(id);
	}

	@PostMapping
	public Lesson addLesson(@RequestBody LessonDto lesson) {
		lessonServise.saveLesson(lesson);
		return null;
	}

	@PutMapping
	public Lesson updateLesson(@RequestBody LessonDto lesson) {
		lessonServise.saveLesson(lesson);
		return null;
	}

	@DeleteMapping("{id}")
	public String deleteLesson(@PathVariable Long id) {
		lessonServise.deleteLesson(id);
		return "Done!";
	}
}
