package com.aesp.repository;
import com.aesp.pojo.AssessmentTemplate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface AssessmentTemplateRepository extends JpaRepository<AssessmentTemplate, Long> {
    
    // Tùy chọn: Tìm Template theo tên 
    Optional<AssessmentTemplate> findByName(String name);
    List<AssessmentTemplate> findAllByDifficultyLevel(String difficulty);
}