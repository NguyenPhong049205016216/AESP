package com.aesp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.aesp.service.AssessmetService;


@Controller
public class MentorAssessmentController {

    private final AssessmetService assessmetService;

    @Autowired
    public MentorAssessmentController (AssessmetService assessmetService ){
        this.assessmetService = assessmetService;
    }

    @GetMapping("/pending")
    public String showPendingAssessments(Model model) {
        Long mentorId = 1L; // Giả định lấy từ Session/Security
        model.addAttribute("pendingAssessments", assessmentService.getPendingAssessments(mentorId));
        return "mentor/assessment_pending_list";
    }

    @PostMapping("/submit_grade")
    public String submitGrade(
        @RequestParam Long assessmentId, @RequestParam int score, 
        @RequestParam String feedback, RedirectAttributes redirectAttributes) 
    {
        try {
            assessmentService.submitAssessmentGrade(assessmentId, score, feedback);
            redirectAttributes.addFlashAttribute("message", "Đã gửi phản hồi thành công.");
            return "redirect:/mentor/assessments/pending"; 
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Lỗi chấm điểm: " + e.getMessage());
            return "redirect:/mentor/assessments/pending";
        }
    }
}
