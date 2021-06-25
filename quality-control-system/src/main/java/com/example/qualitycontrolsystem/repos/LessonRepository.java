package com.example.qualitycontrolsystem.repos;

import org.springframework.data.repository.CrudRepository;
import com.example.qualitycontrolsystem.entity.Lesson;

public interface LessonRepository extends CrudRepository<Lesson, Long>{

}
