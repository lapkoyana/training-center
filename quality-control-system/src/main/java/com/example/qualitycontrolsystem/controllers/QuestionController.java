package com.example.qualitycontrolsystem.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.qualitycontrolsystem.entity.Lesson;
import com.example.qualitycontrolsystem.entity.Question;
import com.example.qualitycontrolsystem.repos.LessonRepository;
import com.example.qualitycontrolsystem.repos.QuestionRepository;
import com.example.qualitycontrolsystem.service.LessonService;
import com.example.qualitycontrolsystem.service.QuestionService;

@Controller
@RequestMapping("/lections")
@PreAuthorize("hasAuthority('LECTURER')")
public class QuestionController {
	
	//correct addresses
	
	@Autowired
	private QuestionService questionService;
	@Autowired
	private LessonService lessonService;
	
	@GetMapping("{lesson}/questions")
    public List<Question> getQuestions(
    		@PathVariable(value = "lesson") Lesson lesson,
    		Model model) {
		
		//here the questions are displayed according to the lecture
		return questionService.getAllQuestions();
    }
	
	//
	@PostMapping("/{lessonId}/questions/{questionId}")
	public String editQuestion(
			@PathVariable(value = "lessonId") Long lessonId,
			@PathVariable(value = "questionId") Long questionId,
			@RequestParam String content) {
		
		Lesson lesson = lessonService.getLesson(lessonId);
		Question question = new Question(content, lesson);
		
		questionService.updateQuestion(question, questionId);
		
		return null;
	}
	
	@PostMapping("/{id}/questions")
	public String addQuestion(
			@PathVariable(value = "id") Long id,
			@RequestParam String content,
			Model model) {
		
		Lesson lesson = lessonService.getLesson(id);
		Question question = new Question(content, lesson);
		
		questionService.addQuestion(question);
		
		return null;
	}
	
	@PostMapping("/{lessonId}/questions/{questionId}/remove")
	public String deleteQuestion(
			@PathVariable(value = "lessonId") Long lessonId,
			@PathVariable(value = "questionId") Long questionId
			) {
		//need to also delete in the lesson object
		Lesson lesson = lessonService.getLesson(lessonId);
		//will need to find out the index
		lesson.getQuestions().remove(0);
		
		questionService.deleteQuestion(questionId);
		
		return null;
	}
}
