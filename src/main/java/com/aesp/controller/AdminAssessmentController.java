package com.aesp.controller;
import com.aesp.service.AssessmentTemplateService;
import com.aesp.service.AssessmentService;
import com.aesp.pojo.Assessment;
import com.aesp.pojo.AssessmentTemplate;
import com.aesp.productinventory.exception.ResourceNotFoundException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin/assessment") 
public class AdminAssessmentController {

    private AssessmentTemplateService assessmentTemplateService;
    private AssessmentService assessmentService;
    @Autowired
    public AdminAssessmentController(AssessmentTemplateService assessmentTemplateService, AssessmentService assessmentService) {
        this.assessmentTemplateService = assessmentTemplateService;
        this.assessmentService = assessmentService;
    }
    @GetMapping("/new")
    public String showCreateForm() {
        return "admin/assessment_creation_form.html"; 
    }
    @PostMapping("/create") 
    public String createTemplate(
        @RequestParam String templateName,
        @RequestParam String questionsJson, 
        @RequestParam String difficulty,
        Model model) 
    {
        try {
            AssessmentTemplate savedTemplate = assessmentTemplateService.createTemplate(
                templateName, questionsJson, difficulty
            );
            model.addAttribute("message", "Tạo mẫu bài tập thành công: " + savedTemplate.getName());
            return "redirect:/admin/assessment/list"; 
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "admin/assessment_creation_form.html"; 
        }
    }
    @GetMapping("/list")
    public String listTemplates(Model model) {
        model.addAttribute("templates", assessmentTemplateService.findAllTemplates());
        return "/admin/templates_list";
    }
    //
    @GetMapping("/pending")
    public String showPendingAssessments(Model model) {
        List<Assessment> pendingList = assessmentService.getPendingAssessments(null);
        model.addAttribute("pendingAssessments", pendingList);
        return "/admin/admin_assign_form";
    }
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long templateId, Model model) {
        
        try {
            // 1. Lấy mẫu bài tập hiện có từ Service
            AssessmentTemplate template = assessmentTemplateService.findTemplateById(templateId);
            
            // 2. Gắn mẫu vào Model để hiển thị lên form
            model.addAttribute("template", template);
            
            // 3. Trả về tên View chỉnh sửa
            return "/admin/assessment_edit_form"; 

        } catch (ResourceNotFoundException e) {
            // Xử lý lỗi nếu không tìm thấy mẫu
            model.addAttribute("errorMessage", "Không tìm thấy mẫu bài tập để sửa: " + templateId);
            return "redirect:admin/assessment/list"; 
        }
    }

    @PostMapping("/submit_grade")
    public String submitGrade(
        @RequestParam Long assessmentId, @RequestParam int score, 
        @RequestParam String feedback, RedirectAttributes redirectAttributes) 
    {
        try {
            assessmentService.submitAssessmentGrade(assessmentId, score, feedback);
            redirectAttributes.addFlashAttribute("message", "Đã gửi phản hồi thành công.");
            return "redirect:/admin/admin_assign_form"; 
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Lỗi chấm điểm: " + e.getMessage());
            return "redirect:/admin/admin_assign_form";
        }
    }
    
}// tạo mẫu bài tập //tạo mẫu gán learner cho mentor
