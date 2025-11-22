package com.aesp.repository;

import com.aesp.pojo.Assessment;
import org.springframework.data.jpa.repository.JpaRepository; 
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface AssessmentRepository extends JpaRepository<Assessment, Long> { 

    List<Assessment> findAllByLearnerId(Long learnerId);

    List<Assessment> findTop5ByMentorId(Long mentorId);

}// KẾ THỪA: Chỉ định Entity (Assessment) và kiểu dữ liệu của Khóa chính (Long)