package com.aesp.repository;

import com.aesp.pojo.LearningPath;
import com.aesp.pojo.Learner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

// Entity: LearningPath
// Kiểu ID: Long (Giả định ID của LearningPath là Long)
@Repository
public interface LearningPathRepository extends JpaRepository<LearningPath, Long> {

    // ===============================================================
    // HÀM CUSTOM FINDER (RẤT QUAN TRỌNG CHO LỘ TRÌNH HỌC)
    // ===============================================================

    /**
     * Tìm kiếm LearningPath theo ID của Learner
     * (Giả sử LearningPath có mối quan hệ OneToOne với Learner và khóa chính là learner_id)
     *
     * @param learnerId ID của Learner
     * @return Optional<LearningPath>
     */
    // Nếu khóa chính của LearningPath chính là learner_id (OneToOne)
    Optional<LearningPath> findByLearnerId(Long learnerId); 
    
    // Nếu mối quan hệ được map là OneToOne, bạn cũng có thể dùng:
    // Optional<LearningPath> findByLearner(Learner learner);
}
