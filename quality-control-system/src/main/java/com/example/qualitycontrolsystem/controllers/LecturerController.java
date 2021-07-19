package com.example.qualitycontrolsystem.controllers;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.qualitycontrolsystem.entity.Lesson;
import com.example.qualitycontrolsystem.service.AnswerService;
import com.example.qualitycontrolsystem.service.LessonService;
import com.example.qualitycontrolsystem.service.UserService;

import java.net.URISyntaxException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@RequestMapping("/lections")
@PreAuthorize("hasAuthority('LECTURER')")
public class LecturerController {
	
	@Autowired
	private LessonService lessonServise;
	
	@Autowired
	private AnswerService answerService;
	
	@Autowired
	private UserService userService;
	
	@Value("${upload.path}")
	private String uploadPath;
	
	@GetMapping
	public List<Lesson> getLessons() {
		return lessonServise.getAllLessons();
	}
	
	@GetMapping("/{id}")
	public Lesson getLesson(@PathVariable Long id) {
		return lessonServise.getLesson(id);
	}
	
	@PostMapping
	public ResponseEntity<Lesson> createLesson(@RequestBody Lesson lesson) throws URISyntaxException {
		lessonServise.addLesson(lesson);
		return null;
//		return ResponseEntity.created(new URI("/lections/" + savedLesson.getId())).body(savedLesson);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Lesson> updateLesson(@PathVariable long id,
											   @RequestBody Lesson lesson) {
		lessonServise.updateLesson(lesson, id);
		return null;
//		return ResponseEntity.ok(currentLesson);
	}
	
	@DeleteMapping
	public ResponseEntity<Lesson> deleteLesson(@PathVariable Long id){
		lessonServise.deleteLesson(id);
		return ResponseEntity.ok().build();
	}
	
//    @GetMapping("/lections")
//    public String lectionsListPage(
//    		Model model,
//    		@RequestParam(required = false) Lesson lesson) {
//    	Iterable<Lesson> lessons = lessonRepository.findAll();
//        model.addAttribute("lessons", lessons);
//        model.addAttribute("lesson", lesson);
//        return "lecturerLectionList";
//    }
//    
//    @GetMapping("/lections/new-lection")
//    public String addLectionPage(
//    		Model model,
//    		@RequestParam(required = false) Lesson lesson) {
//        model.addAttribute("lesson", lesson);
//        return "addLection";
//    }
//    
//    @GetMapping("/lections/{id}")
//    public String editLectionPage(
//    		@PathVariable(value = "id") Long id,
//    		Model model) {
//    	Optional<Lesson> lesson = lessonRepository.findById(id);
//    	ArrayList<Lesson> res = new ArrayList<>();
//    	lesson.ifPresent(res::add);
//        model.addAttribute("lesson", res);
//        return "editLection";
//    }
//    
//    @GetMapping("/students")
//    public String filterQuestionsPage(
//    		Model model) {
//    	ArrayList<Answer> list = new ArrayList<Answer>();
//    	Iterator<Answer> answers = list.iterator();
//    	model.addAttribute("answers", answers);
//    	return "questionByStudent";
//    }
//    
//    @PostMapping("/students")
//    public String filterQuestions(
//    		@RequestParam String filter1,
//    		@RequestParam String filter2,
//    		Model model) {
//    	Iterable<Answer> answers = null;
//    	User user = null;
//		Lesson lesson = null;
//		
//		try {
//			user = userService.findById(Long.valueOf(filter1));
//			lesson = lessonRepository.findById(Long.valueOf(filter2)).orElse(null);
//		} catch (NumberFormatException e) {
//			e.printStackTrace();
//		}
//    	
//    	if(filter1 != null && !filter1.isEmpty() && filter2 != null && !filter2.isEmpty()) {
//    		answers = answerRepository.findByUserAndLesson(user, lesson);
//    	}
//    	
//    	model.addAttribute("answers", answers);
//    	model.addAttribute("lesson", lesson);
//    	model.addAttribute("user", user);
//    	return "questionByStudent";
//    }
//    
//    @PostMapping("/lections/new-lection")
//    public String addLection(
//    		@AuthenticationPrincipal User user,
//    		@RequestParam("topic") String topic,
//    		@RequestParam("dateOfClass") String dateOfClass,
//    		@RequestParam("file") MultipartFile file) throws IOException {
//    	Lesson lesson = new Lesson(topic, dateOfClass, false, user);
//        saveFile(lesson, file);
//        lessonRepository.save(lesson);
//    	
//    	return "redirect:/lections";
//    }
//    
//    @PostMapping("/lections/{id}")
//    public String editLection(
//    		@PathVariable(value = "id") Long id,
//    		@RequestParam String topic,
//    		@RequestParam String dateOfClass,
//    		@RequestParam(required = false) boolean signOfCompleteness,
//    		@RequestParam MultipartFile file) throws IOException {
//    	Lesson lesson = lessonRepository.findById(id).orElse(null);
//    	lesson.setTopic(topic);
//    	lesson.setDateOfClass(dateOfClass);
//        lesson.setSignOfCompleteness(signOfCompleteness);
//
//        saveFile(lesson, file);
//
//        lessonRepository.save(lesson);
//    	
//    	return "redirect:/lections";
//    }
//    
//    @PostMapping("/lections/{id}/remove")
//    public String removeLection(@PathVariable(value = "id") Long id) {
//    	Lesson lesson = lessonRepository.findById(id).orElse(null);
//    	lessonRepository.delete(lesson);
//    	
//    	return "redirect:/lections";
//    }
//
//	private void saveFile(Lesson lesson, MultipartFile file) throws IOException {
//		if (file != null && !file.getOriginalFilename().isEmpty()) {
//    		File uploadFile = new File(uploadPath);
//    		
//    		if(!uploadFile.exists()) {
//    			uploadFile.mkdir();
//    		}
//    		
//    		 String uuidFile = UUID.randomUUID().toString();
//             String resultFilename = uuidFile + "." + file.getOriginalFilename();
//
//             file.transferTo(new File(uploadPath + "/" + resultFilename));
//
//             lesson.setLectureFile(resultFilename);
//    	}
//	}
    
}
