package com.qcs.qualitycontrolsystem.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qcs.qualitycontrolsystem.dto.AnswerDto;
import com.qcs.qualitycontrolsystem.dto.AnswerDtoWithId;
import com.qcs.qualitycontrolsystem.entity.Answer;
import com.qcs.qualitycontrolsystem.entity.Lesson;
import com.qcs.qualitycontrolsystem.entity.Question;
import com.qcs.qualitycontrolsystem.entity.User;
import com.qcs.qualitycontrolsystem.entity.UserLesson;
import com.qcs.qualitycontrolsystem.mapping.AnswerMapping;
import com.qcs.qualitycontrolsystem.repos.AnswerRepository;
import com.qcs.qualitycontrolsystem.repos.LessonRepository;
import com.qcs.qualitycontrolsystem.repos.UserLessonRepository;
import com.qcs.qualitycontrolsystem.repos.UserRepository;

@Service
public class AnswerServiceImpl implements AnswerService {

	@Autowired
	AnswerRepository answerRepository;
	@Autowired
	LessonRepository lessonRepository;
	@Autowired
	AnswerMapping answerMapping;
	@Autowired
	UserLessonRepository userLessonRepository;
	@Autowired
	UserRepository userRepository;

	@Override
	public List<AnswerDtoWithId> getAllAnswers() {
		return answerRepository.findAll().stream().map(answerMapping::mapToAnswerDto).collect(Collectors.toList());
	}

	@Override
	public AnswerDtoWithId getAnswer(long id) {
		return answerMapping.mapToAnswerDto(answerRepository.findById(id).orElseThrow(RuntimeException::new));
	}

	@Override
	public void addAnswer(List<AnswerDto> answersDto, long lessonId, User user) {
		Lesson lesson = lessonRepository.findById(lessonId).orElseThrow();
		List<Answer> answers = new ArrayList<>();

		for (int i = 0; i < answersDto.size(); i++) {
			AnswerDto a = answersDto.get(i);
			Question question = lesson.getQuestions().get(i);
			answers.add(answerMapping.mapToAnswer(a, lesson, question, user));
		}

		answerRepository.saveAll(answers);
		
		UserLesson userLesson = userLessonRepository.findByUserAndLesson(user, lesson);
		userLesson.setSignOfCompleteness(true);
		userLessonRepository.save(userLesson);
	}

	@Override
	public List<AnswerDtoWithId> getAnswersByLessonAndUser(long lessonId, long userId) {
		Lesson lesson = lessonRepository.findById(lessonId).orElseThrow();
		User user = userRepository.findById(userId).orElse(null);
		return answerRepository.findByUserAndLesson(user, lesson).stream().map(answerMapping::mapToAnswerDto)
				.collect(Collectors.toList());
	}

}
