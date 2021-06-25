package com.example.qualitycontrolsystem.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.qualitycontrolsystem.entity.Answer;
import com.example.qualitycontrolsystem.entity.Lesson;
import com.example.qualitycontrolsystem.entity.User;
import com.example.qualitycontrolsystem.repos.AnswerRepository;
import com.example.qualitycontrolsystem.repos.LessonRepository;
import com.example.qualitycontrolsystem.repos.QuestionRepository;
import com.example.qualitycontrolsystem.repos.UserRepository;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

@Controller
@PreAuthorize("hasAuthority('LECTURER')")
public class LecturerController {
	@Autowired
	private LessonRepository lessonRepository;
	@Autowired
	private QuestionRepository questionRepository;
	@Autowired
	private AnswerRepository answerRepository;
	@Autowired
	private UserRepository userRepository;
	
	@Value("${upload.path}")
	private String uploadPath;
	
    @GetMapping("/lections")
    public String lectionsListPage(
    		Model model,
    		@RequestParam(required = false) Lesson lesson) {
    	Iterable<Lesson> lessons = lessonRepository.findAll();
        model.addAttribute("lessons", lessons);
        model.addAttribute("lesson", lesson);
        return "lecturerLectionList";
    }
    
    @GetMapping("/lections/new-lection")
    public String addLectionPage(
    		Model model,
    		@RequestParam(required = false) Lesson lesson) {
        model.addAttribute("lesson", lesson);
        return "addLection";
    }
    
    @GetMapping("/lections/{id}")
    public String editLectionPage(
    		@PathVariable(value = "id") Long id,
    		Model model) {
    	Optional<Lesson> lesson = lessonRepository.findById(id);
    	ArrayList<Lesson> res = new ArrayList<>();
    	lesson.ifPresent(res::add);
        model.addAttribute("lesson", res);
        return "editLection";
    }
    
    @GetMapping("/students")
    public String filterQuestionsPage(
    		Model model) {
    	ArrayList<Answer> list = new ArrayList<Answer>();
    	Iterator<Answer> answers = list.iterator();
    	model.addAttribute("answers", answers);
    	return "questionByStudent";
    }
    
    @PostMapping("/students")
    public String filterQuestions(
    		@RequestParam String filter1,
    		@RequestParam String filter2,
    		Model model) {
    	Iterable<Answer> answers = null;
    	
    	Optional<User> user = userRepository.findById(Long.valueOf(filter1));
    	ArrayList<User> res1 = new ArrayList<>();
    	user.ifPresent(res1::add);
    	
    	Optional<Lesson> lesson = lessonRepository.findById(Long.valueOf(filter2));
    	ArrayList<Lesson> res2 = new ArrayList<>();
    	lesson.ifPresent(res2::add);
    	
    	if(filter1 != null && !filter1.isEmpty() && filter2 != null && !filter2.isEmpty()) {
    		answers = answerRepository.findByUserAndLesson(res1.get(0), res2.get(0));
    	}
    	
    	model.addAttribute("answers", answers);
    	return "questionByStudent";
    }
    
    @PostMapping("/lections/new-lection")
    public String addLection(
    		@AuthenticationPrincipal User user,
    		@RequestParam("topic") String topic,
    		@RequestParam("dateOfClass") String dateOfClass,
    		@RequestParam("file") MultipartFile file) throws IOException {
    	Lesson lesson = new Lesson(topic, dateOfClass, false, user);
        saveFile(lesson, file);
        lessonRepository.save(lesson);
    	
    	return "redirect:/lections";
    }
    
    @PostMapping("/lections/{id}")
    public String editLection(
    		@PathVariable(value = "id") Long id,
    		@RequestParam String topic,
    		@RequestParam String dateOfClass,
    		@RequestParam boolean signOfCompleteness,
    		@RequestParam MultipartFile file) throws IOException {
    	Lesson lesson = lessonRepository.findById(id).orElseThrow();
    	lesson.setTopic(topic);
    	lesson.setDateOfClass(dateOfClass);
        lesson.setSignOfCompleteness(signOfCompleteness);

        saveFile(lesson, file);

        lessonRepository.save(lesson);
    	
    	return "redirect:/lections";
    }
    
    @PostMapping("/lections/{id}/remove")
    public String removeLection(@PathVariable(value = "id") Long id) {
    	Lesson lesson = lessonRepository.findById(id).orElseThrow();
    	lessonRepository.delete(lesson);
    	
    	return "redirect:/lections";
    }

	private void saveFile(Lesson lesson, MultipartFile file) throws IOException {
		if (file != null && !file.getOriginalFilename().isEmpty()) {
    		File uploadFile = new File(uploadPath);
    		
    		if(!uploadFile.exists()) {
    			uploadFile.mkdir();
    		}
    		
    		 String uuidFile = UUID.randomUUID().toString();
             String resultFilename = uuidFile + "." + file.getOriginalFilename();

             file.transferTo(new File(uploadPath + "/" + resultFilename));

             lesson.setLectureFile(resultFilename);
    	}
	}
    
}