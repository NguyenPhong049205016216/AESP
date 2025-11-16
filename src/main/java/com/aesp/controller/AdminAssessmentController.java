package com.aesp.controller;
import com.aesp.service.AssessmentTemplateService;
import com.aesp.pojo.AssessmentTemplate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/templates") 
public class AdminAssessmentController {

    private final AssessmentTemplateService templateService;
    @Autowired
    public AdminAssessmentController(AssessmentTemplateService templateService) {
        this.templateService = templateService;
    }
    // 1. GET: Hiển thị Form tạo Template
    @GetMapping("/new")
    public String showCreateForm() {
        return "admin/template_create_form.html"; 
    }
    // 2. POST: Xử lý dữ liệu form và tạo Template
    @PostMapping("/create")
    public String createTemplate(
        @RequestParam String templateName,
        @RequestParam String questionsJson, // Dữ liệu câu hỏi từ form
        @RequestParam String difficulty,
        Model model) 
    {
        try {
            AssessmentTemplate savedTemplate = templateService.createTemplate(
                templateName, questionsJson, difficulty
            );
            model.addAttribute("message", "Tạo mẫu bài tập thành công: " + savedTemplate.getName());
            // Chuyển hướng đến danh sách các mẫu bài tập
            return "redirect:/admin/templates/list"; 
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
            // Quay lại form với lỗi
            return "admin/template_create_form.html"; 
        }
    }
    // 3. GET: Hiển thị danh sách các Template
    @GetMapping("/list")
    public String listTemplates(Model model) {
        model.addAttribute("templates", templateService.findAllTemplates());
        return "admin/template_list";
    }
}
