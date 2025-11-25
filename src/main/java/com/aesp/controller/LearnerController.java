package com.aesp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.aesp.pojo.Assessment;
import com.aesp.service.AssessmentService;

@Controller
@RequestMapping("/learner")
public class LearnerController {

    @Autowired private AssessmentService assessmentService;

    /**
     * Hiển thị trang làm bài kiểm tra cho Học viên
     * @param assignmentId ID của bài kiểm tra đã được gán (Assessment)
     */
    @GetMapping("/assessment/quiz/{assignmentId}")
    // THÊM 'throws Exception' VÌ SERVICE CŨNG NÉM EXCEPTION
    public String showQuizPage(@PathVariable Long assignmentId, Model model) throws Exception { 
        
        // SỬA DỤNG PHƯƠNG THỨC ĐÃ CÓ TRONG SERVICE
        Assessment assignment = assessmentService.getAssessmentById(assignmentId);
        
        // LOGIC QUAN TRỌNG:
        String quizContentJson = assignment.getCustomContentJson(); 
        
        // Gắn JSON đã chỉnh sửa vào Model
        model.addAttribute("quizContentJson", quizContentJson); 
        model.addAttribute("assignmentId", assignmentId);

        // ...
        return "learner/quiz_page"; 
    }
}//Đảm bảo Học viên luôn làm bài dựa trên nội dung đã được Mentor chỉnh sửa.