package com.aesp.controller;
import com.aesp.service.AssessmentTemplateService;
import com.aesp.pojo.AssessmentTemplate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/assessment") 
public class AdminAssessmentController {

    private AssessmentTemplateService assessmentTemplateService;
    @Autowired
    public AdminAssessmentController(AssessmentTemplateService assessmentTemplateService) {
        this.assessmentTemplateService = assessmentTemplateService;
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
        return "admin/template_list";
    }
    
}// tạo mẫu bài tập
