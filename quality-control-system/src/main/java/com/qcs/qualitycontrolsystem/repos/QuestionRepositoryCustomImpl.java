package com.qcs.qualitycontrolsystem.repos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import org.springframework.stereotype.Component;

import com.qcs.qualitycontrolsystem.entity.Lesson;
import com.qcs.qualitycontrolsystem.entity.Question;

@Component
public class QuestionRepositoryCustomImpl implements QuestionRepositoryCustom {

	@Autowired
	LessonRepository lessonRepository;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<Question> findByLessonId(Long lessonId) {

		List<Question> questions = jdbcTemplate.query("select * from question where lesson_id = ?",
				(ps) -> ps.setFloat(1, lessonId), (rs, rowNum) -> {
					Question question = new Question();
					question.setId(rs.getLong(1));
					question.setContent(rs.getString(2));

					Lesson lesson = lessonRepository.getById(lessonId);
					question.setLesson(lesson);
					return question;
				});
		return questions;
	}

}
