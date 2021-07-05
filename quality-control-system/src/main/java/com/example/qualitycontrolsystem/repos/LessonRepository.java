package com.example.qualitycontrolsystem.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.qualitycontrolsystem.entity.Lesson;

public interface LessonRepository extends JpaRepository<Lesson, Long>{

}
