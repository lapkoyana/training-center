package com.qcs.qualitycontrolsystem.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.qcs.qualitycontrolsystem.entity.Lesson;
import com.qcs.qualitycontrolsystem.entity.User;
import com.qcs.qualitycontrolsystem.entity.UserLesson;

public interface UserLessonRepository extends JpaRepository<UserLesson, Long> {
	UserLesson findByUserAndLesson(User user, Lesson leson);
		
	List<UserLesson> findByUser(User user);
}
