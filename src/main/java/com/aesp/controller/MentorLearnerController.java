package com.aesp.controller;

import com.aesp.service.AssessmentService;
import com.aesp.service.LearnerManagementService;
import com.aesp.pojo.AssessmentTemplate;
import com.aesp.pojo.Learner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/mentor/learners")
public class MentorLearnerController {

    private final LearnerManagementService learnerManagementService;
    private final AssessmentService assessmentService;

    @Autowired
    public MentorLearnerController(LearnerManagementService learnerManagementService, AssessmentService assessmentService) {
        this.learnerManagementService = learnerManagementService;
        this.assessmentService = assessmentService;
    }

    @GetMapping("/list")
    public String listMentorLearners(Model model) {
        Long currentMentorId = 1L; // <<< Giả định: Lấy ID Mentor từ Session
        List<Learner> mentorLearners = learnerManagementService.findLearnersByMentorId(currentMentorId);

        model.addAttribute("learners", mentorLearners);
        return "mentor/mentor_learner_management";
    }// 1. GET: Hiển thị danh sách Học viên của Mentor

    @GetMapping("/{learnerId}/report")
    public String viewLearnerReport(@PathVariable Long learnerId, Model model) {
        try {
            Learner learner = learnerManagementService.findLearnerById(learnerId);
            Object progressData = learnerManagementService.getProgressReport(learnerId);
            model.addAttribute("learner", learner);
            model.addAttribute("report", progressData);
            return "mentor/learner_report_detail";

        } catch (Exception e) {
            model.addAttribute("errorMessage", "Không thể xem báo cáo: " + e.getMessage());
            return "redirect:/aesp/mentor/learners/list?error=" + e.getMessage();
        }
    }// 2. GET: Xem chi tiết báo cáo tiến độ của 1 học viên

    @GetMapping("/assign")
    public String showAssignAssessmentForm(@RequestParam Long learnerId, Model model) {
        // Logic: Load danh sách Assessment Templates
        // Logic: Load chi tiết Learner
        return "mentor/assessment_assign_form";
    }// 3. GET: Gán Assessment cho học viên (Dẫn đến Form Assessment)

    // Thêm hàm showTemplateList ở đây
    @GetMapping("/assignment/template-list")
    public String showTemplateList(Model model) {
        List<AssessmentTemplate> templates = assessmentService.findAllTemplates();
        model.addAttribute("templates", templates);
        return "mentor/template_list";
    }
}// Quản lý learner và theo dỏi tiếng độ cho mentor