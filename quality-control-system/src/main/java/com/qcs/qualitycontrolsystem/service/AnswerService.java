package com.qcs.qualitycontrolsystem.service;

import java.util.List;

import com.qcs.qualitycontrolsystem.entity.Answer;
import com.qcs.qualitycontrolsystem.entity.Lesson;
import com.qcs.qualitycontrolsystem.entity.User;

public interface AnswerService {

	public List<Answer> getAllAnswers();

	public Answer getAnswer(long id);

	public void addAnswer(List<Answer> answer, Lesson lesson);

	public List<Answer> getAnswersByLessonAndUser(Lesson lesson, User user);

}
