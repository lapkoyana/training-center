package com.example.qualitycontrolsystem.controllers;

import java.util.List;
import java.util.Set;

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

@Controller
@RequestMapping("/lections")
@PreAuthorize("hasAuthority('LECTURER')")
public class QuestionController {
	@Autowired
	private QuestionRepository questionRepository;
	@Autowired
	private LessonRepository lessonRepository;
	
	@GetMapping("{lesson}/questions")
    public String questionListPage(
    		@PathVariable(value = "lesson") Lesson lesson,
    		@RequestParam(required = false) Question question,
    		Model model) {
		List<Question> questions = lesson.getQuestions();
        model.addAttribute("questions", questions);
        model.addAttribute("lesson", lesson);
        return "questionList";
    }
	
	@PostMapping("/{lessonId}/questions/{questionId}")
	public String editQuestion(
			@PathVariable(value = "lessonId") Long lessonId,
			@PathVariable(value = "questionId") Long questionId,
			@RequestParam String content) {
		Question question = questionRepository.findById(questionId).orElseThrow();
		question.setContent(content);
		questionRepository.save(question);
		return "redirect:/lections/{lessonId}/questions";
	}
	
	@PostMapping("/{id}/questions")
	public String addQuestion(
			@PathVariable(value = "id") Long id,
			@RequestParam String content,
			Model model) {
		Lesson lesson = lessonRepository.findById(id).orElseThrow();
		Question question = new Question(content, lesson);
		lesson.getQuestions().add(question);
		
		model.addAttribute("lesson", lesson);
		
		questionRepository.save(question);
		
		return "redirect:/lections/{id}/questions";
	}
	
	@PostMapping("/{lessonId}/questions/{questionId}/remove")
	public String deleteQuestion(
			@PathVariable(value = "lessonId") Long lessonId,
			@PathVariable(value = "questionId") Long questionId
			) {
		Lesson lesson = lessonRepository.findById(lessonId).orElseThrow();
		Question question = questionRepository.findById(questionId).orElseThrow();
		
		lesson.getQuestions().remove(question);
		questionRepository.delete(question);
		
		return "redirect:/lections/{lessonId}/questions";
	}
}
