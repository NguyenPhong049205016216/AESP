package com.aesp.service;

import com.aesp.pojo.Assessment;
import com.aesp.pojo.AssessmentTemplate;
import com.aesp.pojo.Learner;
import com.aesp.pojo.Mentor;
import com.aesp.repository.AssessmentRepository;
import com.aesp.repository.AssessmentTemplateRepository;
import com.aesp.repository.LearnerRepository;
import com.aesp.repository.MentorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AssignmentService {

    @Autowired private AssessmentRepository assessmentRepository;
    @Autowired private AssessmentTemplateRepository templateRepository;
    @Autowired private LearnerRepository learnerRepository;
    @Autowired private MentorRepository mentorRepository; // Giả định đã có Repositories

    /**
     * Lấy mẫu bài tập gốc của Admin để Mentor chỉnh sửa
     * @param templateId ID của mẫu Admin tạo
     * @return AssessmentTemplate
     */
    public AssessmentTemplate getTemplateForEditing(Long templateId) {
        return templateRepository.findById(templateId)
        .orElseThrow(() -> new RuntimeException("Không tìm thấy mẫu bài tập."));
    }
    /**
     * Tạo và Gán Bài kiểm tra cá nhân hóa cho Học viên (Bước Mentor)
     * @param learnerId ID Học viên
     * @param mentorId ID Mentor
     * @param templateId ID Mẫu gốc
     * @param customizedJson Nội dung JSON đã được Mentor chỉnh sửa
     * @return Assessment (Bài kiểm tra vừa được tạo)
     */
    public Assessment createCustomAssignment(Long learnerId, Long mentorId, Long templateId, String customizedJson) {
        
        Learner learner = learnerRepository.findById(learnerId)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy Học viên."));
        Mentor mentor = mentorRepository.findById(mentorId)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy Mentor."));
        AssessmentTemplate template = templateRepository.findById(templateId)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy Mẫu gốc."));
        
        Assessment newAssignment = new Assessment();
        newAssignment.setLearner(learner);
        newAssignment.setMentor(mentor);
        newAssignment.setTemplate(template);
        
        // LƯU CÂU HỎI ĐÃ CHỈNH SỬA VÀO TRƯỜNG MỚI
        newAssignment.setCustomContentJson(customizedJson); 
        
        newAssignment.setStatus("PENDING"); // Đang chờ làm
        // newAssignment.setAssignedDate(LocalDateTime.now()); // Thêm trường này nếu cần

        return assessmentRepository.save(newAssignment);
    }
}
