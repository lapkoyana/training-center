package com.example.qualitycontrolsystem.repos;

import java.util.List;

import com.example.qualitycontrolsystem.entity.Lesson;
import com.example.qualitycontrolsystem.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.qualitycontrolsystem.entity.Answer;

public interface AnswerRepository extends JpaRepository<Answer, Long>{
	List<Answer> findByUserAndLesson(User user, Lesson lesson);
}
