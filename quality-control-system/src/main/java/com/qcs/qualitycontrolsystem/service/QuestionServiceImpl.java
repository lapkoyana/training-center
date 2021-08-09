package com.qcs.qualitycontrolsystem.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qcs.qualitycontrolsystem.dto.QuestionDto;
import com.qcs.qualitycontrolsystem.dto.QuestionDtoWithId;
import com.qcs.qualitycontrolsystem.entity.Lesson;
import com.qcs.qualitycontrolsystem.entity.Question;
import com.qcs.qualitycontrolsystem.mapping.QuestionMapping;
import com.qcs.qualitycontrolsystem.repos.LessonRepository;
import com.qcs.qualitycontrolsystem.repos.QuestionRepository;

@Service
public class QuestionServiceImpl implements QuestionService {

	@Autowired
	QuestionRepository questionRepository;

	QuestionMapping questionMapping;
	@Autowired
	LessonRepository lessonRepository;

	@Override
	public List<QuestionDtoWithId> getAllQuestions() {
		return questionRepository.findAll().stream().map(questionMapping::mapToQuestionDto)
				.collect(Collectors.toList());
	}

	@Override
	public QuestionDtoWithId getQuestion(long id) {
		return questionMapping.mapToQuestionDto(questionRepository.findById(id).orElseThrow(RuntimeException::new));
	}

	@Override
	public void addQuestion(long lessonId, QuestionDto questionDto) {
		Lesson lesson = lessonRepository.getById(lessonId);
		Question question = questionMapping.mapToQuestion(questionDto, lesson);

		lesson.getQuestions().add(question);
		question.setLesson(lesson);

		questionRepository.save(question);
	}

	@Override
	public void updateQuestion(long lessonId, QuestionDtoWithId questionDto) {
		Lesson lesson = lessonRepository.findById(lessonId).orElseThrow();

		Question newQuestion = questionMapping.mapToQuestion(questionDto, lesson);
		Question questionFromDb = questionRepository.findById(newQuestion.getId()).orElseThrow();

		lesson.getQuestions().remove(questionFromDb);
		lesson.getQuestions().add(newQuestion);

		questionRepository.save(newQuestion);
	}

	@Override
	public void deleteQuestion(long lessonId, long questionId) {
		Question question = questionRepository.findById(questionId).orElseThrow();
		Lesson lesson = lessonRepository.findById(lessonId).orElseThrow();

		lesson.getQuestions().remove(question);

		questionRepository.deleteById(questionId);
	}

	@Override
	public List<QuestionDtoWithId> getQuestionsByLesson(long lessonId) {
		return questionRepository.findByLessonId(lessonId).stream().map(questionMapping::mapToQuestionDto)
				.collect(Collectors.toList());
	}
}
