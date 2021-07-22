package com.qcs.qualitycontrolsystem.repos;

import java.util.List;

import com.qcs.qualitycontrolsystem.entity.Question;

public interface QuestionRepositoryCustom {
	List<Question> findByLessonId(Long lessonId);
}
