package com.example.qualitycontrolsystem.controllers;

import java.time.LocalDate;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.qualitycontrolsystem.entity.Answer;
import com.example.qualitycontrolsystem.entity.Lesson;
import com.example.qualitycontrolsystem.entity.Question;
import com.example.qualitycontrolsystem.entity.User;
import com.example.qualitycontrolsystem.repos.AnswerRepository;
import com.example.qualitycontrolsystem.repos.LessonRepository;
import com.example.qualitycontrolsystem.repos.UserRepository;

@Controller
@RequestMapping("/completed-lections")
@PreAuthorize("hasAuthority('STUDENT')")
public class StudentController {
	@Autowired
	private LessonRepository lessonRepos;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private AnswerRepository answerRepository;
	
    @GetMapping("/{user}")
    public String completedLectionsList( 
    		Model model,
    		@PathVariable User user) {
        Iterable<Lesson> lessons = lessonRepos.findAll();
        model.addAttribute("lessons", lessons);
        return "studentLectionList";
    }
    
    @GetMapping("/{userId}/interview/{lessonId}")
    public String interviewPage(
    		@PathVariable(value = "userId") Long userId,
    		@PathVariable(value = "lessonId") Long lessonId,
    		Model model) {
    	Lesson lesson = lessonRepos.findById(lessonId).orElseThrow();
    	Set<Question> questions = lesson.getQuestions();
    	model.addAttribute("lesson",lesson);
    	model.addAttribute("questions", questions);
    	return "interview";
    }
    
    @PostMapping("/{userId}/interview/{lessonId}")
    public String sendInterview(
    		@PathVariable(value = "userId") Long userId,
    		@PathVariable(value = "lessonId") Long lessonId,
    		@RequestParam String answerContent) {
    	Lesson lesson = lessonRepos.findById(lessonId).orElseThrow();
    	User user = userRepository.findById(userId).orElseThrow();
    	
    	String[] contentOfAnswers = answerContent.split(",");
    	Set<Question> questions = lesson.getQuestions();
    	int i = 0;
    	for(Question q : questions) {
    		Answer answer = new Answer(LocalDate.now().toString(), contentOfAnswers[i], user, q, lesson);
    		user.getAnswer().add(answer);
    		answerRepository.save(answer);
    		i++;
    	}
//    	Set<Question> questions = lesson.getQuestions();
//    	for(Question q : questions) {
//    		Answer answer = new Answer(LocalDate.now().toString(), answerContent, user, q, lesson);
//    		user.getAnswer().add(answer);
//    		answerRepository.save(answer);
//    	}
    	return "redirect:/completed-lections/{userId}";
	}
}

