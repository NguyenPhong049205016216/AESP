package com.aesp.controller;

import com.aesp.service.FeedbackService; 
import com.aesp.pojo.Feedbacks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/admin/feedback") // URL GỐC: /admin/feedback
public class AdminFeedbackController {

    private final FeedbackService feedbackService;

    @Autowired
    public AdminFeedbackController(FeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }

    // =======================================================
    // 1. GET: Hiển thị danh sách Feedback CẦN KIỂM DUYỆT
    // URL: /admin/feedback/pending
    // =======================================================
    @GetMapping("/pending") 
    public String listPendingFeedback(Model model) {
        
        // 1. Logic: Gọi Service để lấy danh sách cần kiểm duyệt
        // LƯU Ý: Phải gọi hàm findPendingFeedback() thực tế (Không dùng List.of() cứng)
        List<Feedbacks> pendingList = feedbackService.findPendingFeedback(); 
        
        model.addAttribute("pendingFeedbacks", pendingList);
        
        // 2. Trả về View (Phải khớp với file HTML/JSP)
        return "admin/admin_feedback_moderation"; 
    }

    // =======================================================
    // 2. GET: Xử lý PHÊ DUYỆT Feedback
    // URL: /admin/feedback/approve?id={feedbackId}
    // =======================================================
    @GetMapping("/approve")
    public String approveFeedback(@RequestParam Long id, RedirectAttributes redirectAttributes) {
        try {
            // Logic: Đổi trạng thái Feedback từ PENDING sang APPROVED
            feedbackService.updateFeedbackStatus(id, "APPROVED");
            redirectAttributes.addFlashAttribute("message", "Đã phê duyệt Feedback thành công.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Lỗi phê duyệt: " + e.getMessage());
        }
        // Chuyển hướng về trang danh sách đang chờ
        return "redirect:/admin/feedback/pending"; // Sửa lại để dùng URL tương đối
    }
    
    // =======================================================
    // 3. GET: Xử lý XÓA Feedback (Xóa vĩnh viễn)
    // URL: /admin/feedback/delete?id={feedbackId}
    // =======================================================
    @GetMapping("/delete")
    public String deleteFeedback(@RequestParam Long id, RedirectAttributes redirectAttributes) {
        try {
            // Logic: Xóa vĩnh viễn Feedback
            feedbackService.deleteFeedback(id);
            redirectAttributes.addFlashAttribute("message", "Đã xóa Feedback thành công.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Lỗi xóa: " + e.getMessage());
        }
        // Chuyển hướng về trang danh sách đang chờ
        return "redirect:/admin/feedback/pending"; // Sửa lại để dùng URL tương đối
    }
}