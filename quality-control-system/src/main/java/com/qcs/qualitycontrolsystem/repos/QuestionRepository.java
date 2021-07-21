package com.qcs.qualitycontrolsystem.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.qcs.qualitycontrolsystem.entity.Lesson;
import com.qcs.qualitycontrolsystem.entity.Question;

public interface QuestionRepository extends JpaRepository<Question, Long>{
	List<Question> findByLesson(Lesson lesson);
}
