package com.example.qualitycontrolsystem.repos;

import org.springframework.data.repository.CrudRepository;
import com.example.qualitycontrolsystem.entity.Question;

public interface QuestionRepository extends CrudRepository<Question, Long>{

}
