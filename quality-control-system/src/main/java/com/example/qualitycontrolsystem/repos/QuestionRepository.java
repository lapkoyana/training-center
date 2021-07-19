package com.example.qualitycontrolsystem.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.qualitycontrolsystem.entity.Question;

public interface QuestionRepository extends JpaRepository<Question, Long>{

}
