package com.qcs.qualitycontrolsystem.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.qcs.qualitycontrolsystem.dto.LessonDto;
import com.qcs.qualitycontrolsystem.dto.LessonDtoWithId;
import com.qcs.qualitycontrolsystem.dto.LessonDtoWithIdResp;
import com.qcs.qualitycontrolsystem.dto.UserLessonDto;
import com.qcs.qualitycontrolsystem.entity.User;

public interface LessonService {

	public List<LessonDtoWithIdResp> getAllLessons();

	public LessonDtoWithIdResp getLesson(long id);

	public void addLesson(LessonDto lesson, MultipartFile file);

	public void updateLesson(LessonDtoWithId lesson, MultipartFile file);

	public void deleteLesson(long id);

	public UserLessonDto getSignOfCompletenessForUserAndLesson(long lessonId, User user);
}