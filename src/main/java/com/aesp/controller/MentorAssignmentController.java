package com.aesp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.aesp.pojo.AssessmentTemplate;
import com.aesp.service.AssignmentService;
import com.aesp.service.AssessmentService; // Cần thiết để lấy danh sách Templates

@Controller
@RequestMapping("/mentor/assignment")
public class MentorAssignmentController {

    @Autowired private AssignmentService assignmentService;
    @Autowired private AssessmentService assessmentService; // Dùng để lấy templates

    // Giả định: Có Repositories cho Learner và Template để lấy danh sách
    // @Autowired private LearnerRepository learnerRepository; 

    /**
     * [1] Hiển thị danh sách mẫu bài tập để Mentor chọn mẫu nào sẽ chỉnh sửa (Bước bắt đầu)
     * URL: /mentor/assignment/template-list
     * Dẫn tới: mentor/template_list.html
     */
    @GetMapping("/template_list")
    public String showTemplateListForAssignment(Model model) {
        
        // Lấy danh sách templates từ Service
        model.addAttribute("templates", assessmentService.findAllTemplates());
        
        return "mentor/template_list"; 
    }

    /**
     * [2] Hiển thị Form cho Mentor chọn Học viên, xem mẫu và bắt đầu chỉnh sửa.
     * URL: /mentor/assignment/edit-and-assign/{templateId}
     * Dẫn tới: mentor/assignment_editor.html
     */
    @GetMapping("/edit-and-assign/{templateId}")
    public String showAssignmentForm(@PathVariable Long templateId, Model model) {
        
        AssessmentTemplate template = assignmentService.getTemplateForEditing(templateId);
        
        // Lấy danh sách Học viên (Nếu đã Autowired LearnerRepository)
        // model.addAttribute("learners", learnerRepository.findAll());
        
        model.addAttribute("template", template);
        // Chuyển nội dung JSON gốc vào model để Mentor thấy
        model.addAttribute("initialContent", template.getContentJson()); 
        
        return "mentor/assignment_editor"; 
    }
    
    /**
     * [3] Xử lý việc Gán Bài tập sau khi Mentor đã chỉnh sửa nội dung.
     * URL: /mentor/assignment/assign
     */
    @PostMapping("/assign")
    public String handleAssignment(@RequestParam Long learnerId, 
    @RequestParam Long mentorId, // Thực tế lấy từ Session/Security
    @RequestParam Long templateId,
    @RequestParam String customContentJson) { 

        // Gọi Service để tạo Entity Assessment mới
        assignmentService.createCustomAssignment(learnerId, mentorId, templateId, customContentJson);
        
        // Chuyển hướng về Dashboard/Trang quản lý bài tập của Mentor
        return "redirect:/mentor/dashboard"; 
    }
}