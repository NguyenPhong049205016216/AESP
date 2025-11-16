package com.aesp.service;

import com.aesp.pojo.Assessment;
import com.aesp.pojo.Learner;
import com.aesp.pojo.LearningPath;
import com.aesp.repository.AssessmentRepository;
import com.aesp.repository.LearningPathRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AssessmetService {
    
    //private final AssessmentRepository assessmentRepository;
    //private final LearningPathRepository learningPathRepository; // Cần tạo Repository này
    /* 
    @Autowired
    public AssessmentService(AssessmentRepository assessmentRepository, 
        LearningPathRepository learningPathRepository) {
        this.assessmentRepository = assessmentRepository;
        this.learningPathRepository = learningPathRepository;
    }
    */

    /**
     * Xử lý kết quả bài kiểm tra và cập nhật cấp độ cho học viên.
     */
    /* 
    @Transactional
    public Assessment completeAssessment(Assessment newAssessment, Long learnerId) throws Exception {
        
        // 1. Logic Tính toán Cấp độ Mới
        //String newLevel = calculateLevel(newAssessment.getScore());
        
        // 2. Lưu kết quả bài kiểm tra
        //Assessment savedAssessment = assessmentRepository.save(newAssessment);
        
        // 3. Cập nhật LearningPath của học viên
        //LearningPath path = learningPathRepository.findByLearnerId(learnerId)
        //.orElseThrow(() -> new Exception("Không tìm thấy lộ trình học."));
        
        //path.setCurrentLevel(newLevel);
        // Cần tính toán lại tiến trình (ví dụ: path.setProgress())
        //learningPathRepository.save(path);
        
        //return savedAssessment;
    }
    
    /**
     * Hàm giả lập logic tính toán cấp độ dựa trên điểm số
     */
    private String calculateLevel(int score) {
        if (score >= 90) return "Advanced";
        if (score >= 70) return "Upper-Intermediate";
        if (score >= 50) return "Intermediate";
        return "Beginner";
    }
}
