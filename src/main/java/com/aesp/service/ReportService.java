package com.aesp.service;

import com.aesp.dao.ReportDTO;
import com.aesp.repository.LearnerRepository;
import com.aesp.repository.MentorRepository;
import com.aesp.repository.AssessmentRepository;
import com.aesp.repository.FeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReportService {

    @Autowired
    private LearnerRepository learnerRepository;
    @Autowired
    private MentorRepository mentorRepository;
    @Autowired
    private AssessmentRepository assessmentRepository;
    @Autowired
    private FeedbackRepository feedbacksRepository;

    /**
     * Tạo báo cáo tổng quan bao gồm các số liệu chính
     * @return ReportDTO chứa các số liệu
     */
    public ReportDTO generateOverallReport() {
    
        long totalStudents = learnerRepository.count();

        long totalMentors = mentorRepository.count();

        long completedAssessments = assessmentRepository.countCompletedAssessments();
        
        long pendingFeedbacks = feedbacksRepository.countByStatus("PENDING");

        return new ReportDTO(totalStudents, totalMentors, completedAssessments, pendingFeedbacks);
    }
}