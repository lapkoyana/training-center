package com.qcs.qualitycontrolsystem.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.qcs.qualitycontrolsystem.entity.Answer;
import com.qcs.qualitycontrolsystem.entity.Lesson;
import com.qcs.qualitycontrolsystem.entity.User;

public interface AnswerRepository extends JpaRepository<Answer, Long>{
	List<Answer> findByUserAndLesson(User user, Lesson lesson);
}
