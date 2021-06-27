package com.example.qualitycontrolsystem.repos;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.qualitycontrolsystem.entity.Lesson;
import com.example.qualitycontrolsystem.entity.User;
import com.example.qualitycontrolsystem.entity.UserLesson;

public interface UserLessonRepository extends CrudRepository<UserLesson, Long>{
	List<UserLesson> findByUser(User user);
}
