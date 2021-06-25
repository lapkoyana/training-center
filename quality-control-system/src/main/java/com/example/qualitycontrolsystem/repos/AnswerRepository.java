package com.example.qualitycontrolsystem.repos;

import java.util.List;

import com.example.qualitycontrolsystem.entity.Lesson;
import com.example.qualitycontrolsystem.entity.User;

import org.springframework.data.repository.CrudRepository;
import com.example.qualitycontrolsystem.entity.Answer;

public interface AnswerRepository extends CrudRepository<Answer, Long>{
	List<Answer> findByUserAndLesson(User user, Lesson lesson);
}
