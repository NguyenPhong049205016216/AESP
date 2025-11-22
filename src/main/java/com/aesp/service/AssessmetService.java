package com.aesp.service;

import com.aesp.pojo.Assessment;
import com.aesp.repository.AssessmentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AssessmetService {
    //Chấm điểm, quản lý sevice
    
    private final AssessmentRepository assessmentRepository;

    
    @Autowired
    public AssessmetService(AssessmentRepository assessmentRepository) {
        this.assessmentRepository = assessmentRepository;
    }
    
    public List<Assessment> getPendingAssessments(Long mentorId) {
        // Lấy danh sách cần chấm điểm của Mentor
        return assessmentRepository.findTop5ByMentorId(mentorId); 
    }
    
    public Assessment getAssessmentById(Long assessmentId) throws Exception {
        return assessmentRepository.findById(assessmentId)
        .orElseThrow(() -> new Exception("Bài kiểm tra không tồn tại."));
    }

    @Transactional
    public void submitAssessmentGrade(Long assessmentId, int score, String feedback) throws Exception {
        Assessment assessment = getAssessmentById(assessmentId);
        
        // Logic chấm điểm: Cập nhật score và feedback
        assessment.setScore(score);
        assessment.setFeedback(feedback);
        // Cần logic cập nhật LearningPath/Level ở đây
        assessmentRepository.save(assessment);
    }
}
