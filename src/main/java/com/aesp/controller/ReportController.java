package com.aesp.controller;

import com.aesp.dao.ReportDTO;
import com.aesp.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/reports")
public class ReportController {

    @Autowired
    private ReportService reportService;

    /**
     * Hiển thị trang Thống kê & Báo cáo
     * @param model Đối tượng Model cho Thymeleaf
     * @return Tên View (template)
     */
    @GetMapping
    public String showStatisticsReport(Model model) {
        
        // Gọi Service để lấy dữ liệu báo cáo tổng quát
        ReportDTO reportData = reportService.generateOverallReport();
        
        // Gắn các thuộc tính vào Model để sử dụng trong HTML (statistics_report.html)
        model.addAttribute("totalStudents", reportData.getTotalStudents());
        model.addAttribute("totalMentors", reportData.getTotalMentors());
        model.addAttribute("completedAssessments", reportData.getCompletedAssessments());
        model.addAttribute("pendingFeedbacks", reportData.getPendingFeedbacks());
        
        // Trả về tên file HTML (đã tạo ở câu trả lời trước)
        return "admin/admin_statistics_report";
    }
    
    // Thêm các phương thức khác cho báo cáo chi tiết nếu cần
    // Ví dụ: showStudentDetailReport(), showMentorPerformanceReport(), v.v.
}
