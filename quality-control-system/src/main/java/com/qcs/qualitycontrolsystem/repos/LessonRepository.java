package com.qcs.qualitycontrolsystem.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import com.qcs.qualitycontrolsystem.entity.Lesson;

public interface LessonRepository extends JpaRepository<Lesson, Long> {

}
