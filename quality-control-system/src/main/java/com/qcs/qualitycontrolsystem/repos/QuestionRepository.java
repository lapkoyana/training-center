package com.qcs.qualitycontrolsystem.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import com.qcs.qualitycontrolsystem.entity.Question;

public interface QuestionRepository extends JpaRepository<Question, Long>, QuestionRepositoryCustom {
}
