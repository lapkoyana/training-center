package com.qcs.qualitycontrolsystem.service;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.qcs.qualitycontrolsystem.dto.LessonDto;
import com.qcs.qualitycontrolsystem.dto.LessonDtoWithId;
import com.qcs.qualitycontrolsystem.dto.LessonDtoWithIdResp;
import com.qcs.qualitycontrolsystem.entity.Lesson;
import com.qcs.qualitycontrolsystem.entity.Role;
import com.qcs.qualitycontrolsystem.entity.User;
import com.qcs.qualitycontrolsystem.entity.UserLesson;
import com.qcs.qualitycontrolsystem.mapping.LessonMapping;
import com.qcs.qualitycontrolsystem.repos.LessonRepository;

@Service
public class LessonServiceImpl implements LessonService {

	@Autowired
	private LessonRepository lessonRepository;
	@Autowired
	private LessonMapping lessonMapping;
	@Autowired
	private UserService userService;

	@Value("${upload.path}")
	private String uploadPath;

	@Override
	public List<LessonDtoWithIdResp> getAllLessons() {
		return lessonRepository.findAll().stream().map(lessonMapping::mapToLessonDto).collect(Collectors.toList());
	}

	@Override
	public LessonDtoWithIdResp getLesson(long id) {
		return lessonMapping.mapToLessonDto(lessonRepository.findById(id).orElse(null));
	}

	@Override
	public void addLesson(LessonDto lessonDto, MultipartFile file) {
		Lesson lesson;
		try {
			lesson = lessonMapping.mapToLesson(lessonDto);
			saveFile(lesson, file);

			List<User> allStudents = userService.getAllUsers().stream()
					.filter(user -> user.getRole().iterator().next().equals(Role.STUDENT)).collect(Collectors.toList());

			Set<UserLesson> userLessonsForThisLesson = new HashSet<>();
			allStudents.forEach(student -> {
				UserLesson userLesson = new UserLesson(student, lesson);
				userLessonsForThisLesson.add(userLesson);
			});
			lesson.setUserLesson(userLessonsForThisLesson);

			lessonRepository.save(lesson);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateLesson(LessonDtoWithId lessonDto, MultipartFile file) {
		Lesson lesson;
		try {
			lesson = lessonMapping.mapToLesson(lessonDto);
			saveFile(lesson, file);
			lessonRepository.save(lesson);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteLesson(long id) {
		lessonRepository.deleteById(id);
	}

	private void saveFile(Lesson lesson, MultipartFile file) {
		if (file != null && !file.getOriginalFilename().isEmpty()) {
			File uploadFile = new File(uploadPath);

			if (!uploadFile.exists()) {
				uploadFile.mkdir();
			}

			String uuidFile = UUID.randomUUID().toString();
			String resultFilename = uuidFile + "." + file.getOriginalFilename();

			try {
				file.transferTo(new File(uploadPath + "/" + resultFilename));
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
			}

			lesson.setLectureFile(resultFilename);
		}
	}

	@Override
	public Resource load(String filename) {
	    try {
			Path file = Paths.get("uploads").resolve(filename);
			Resource resource = new UrlResource(file.toUri());
			
			if (resource.exists() || resource.isReadable()) {
			       return resource;
		    } else {
		        throw new RuntimeException("Could not read the file!");
		    }
	    } catch (MalformedURLException e) {
	      throw new RuntimeException("Error: " + e.getMessage());
	    }
	}
}
