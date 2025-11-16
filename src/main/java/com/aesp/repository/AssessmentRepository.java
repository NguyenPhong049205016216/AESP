package com.aesp.repository;

import com.aesp.pojo.Assessment;
import org.springframework.data.jpa.repository.JpaRepository; 
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
// 2. KẾ THỪA: Chỉ định Entity (Assessment) và kiểu dữ liệu của Khóa chính (Long)
public interface AssessmentRepository extends JpaRepository<Assessment, Long> { 

    List<Assessment> findAllByLearnerId(Long learnerId);

    //List<Assessment> findAllByLearnerIdOrderByAssessmentDateDesc(Long learnerId);
 
    //List<Assessment> findTop5ByLearnerIdOrderByAssessmentDateDesc(Long learnerId);
}