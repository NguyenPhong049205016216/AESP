package com.aesp.service;

import com.aesp.pojo.Assessment;
import com.aesp.pojo.AssessmentTemplate;
import com.aesp.repository.AssessmentRepository;
import com.aesp.repository.AssessmentTemplateRepository;
import com.aesp.productinventory.exception.ResourceNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
public class AssessmentService {
    //Chấm điểm, quản lý sevice
    
    private final AssessmentRepository assessmentRepository;
    private final AssessmentTemplateRepository templateRepository;

    @Autowired
    public AssessmentService(AssessmentRepository assessmentRepository, AssessmentTemplateRepository assessmentTemplaterRepository) {
        this.assessmentRepository = assessmentRepository;
        this.templateRepository = assessmentTemplaterRepository;
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
    /**
     * Lấy danh sách tất cả các mẫu bài tập (templates).
     * @return List<AssessmentTemplate>
     */
    public List<AssessmentTemplate> findAllTemplates() {
        // Trả về tất cả templates
        return templateRepository.findAll();
    }
    
    /**
     * Lấy một mẫu bài tập theo ID.
     * @param id ID của mẫu bài tập.
     * @return AssessmentTemplate
     * @throws ResourceNotFoundException nếu không tìm thấy.
     */
    public AssessmentTemplate findTemplateById(Long id) { 
    return templateRepository.findById(id)
    .orElseThrow(() -> new ResourceNotFoundException("Mẫu bài tập không tồn tại."));
}
}
