package com.aesp.controller;

import com.aesp.service.UserService;
import com.aesp.pojo.Learner;
import com.aesp.pojo.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

// Đặt tên class rõ ràng: RegistrationServlet
public class Ctroller_Register extends HttpServlet { 
    
    private UserService userService; // <<< CHỈ DÙNG SERVICE

    @Override
    public void init() {
        // Khởi tạo UserService ở đây
        this.userService = new UserService(); 
    }

    // doGet: Giữ nguyên để hiển thị form

    /**
     * Phương thức POST: Xử lý dữ liệu khi người dùng nhấn nút "Đăng ký"
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException {
        
        // Cần thiết lập Encoding để đọc tiếng Việt
        request.setCharacterEncoding("UTF-8"); 

        // 1. Lấy dữ liệu từ form (SỬA LỖI TÊN THAM SỐ)
        String name = request.getParameter("name");
        String email = request.getParameter("email"); // <<< SỬA: Lấy từ name="email"
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirm-password"); 
        String goal = request.getParameter("goal");
        
        // 2. Kiểm tra nghiệp vụ cơ bản (nên nằm ở Service, nhưng kiểm tra password khớp là cần thiết ở đây)
        if (!password.equals(confirmPassword)) {
            request.setAttribute("errorMessage", "Mật khẩu và Xác nhận mật khẩu không khớp.");
            doGet(request, response); 
            return;
        }

        // 3. Tạo đối tượng Learner (Giả định: form này chỉ dành cho Learner)
        Learner newLearner = new Learner();
        newLearner.setName(name);
        newLearner.setEmail(email); 
        newLearner.setGoal(goal);
        // Không set Role/Status/Password/CreatedAt/UpdatedAt ở đây, để Service lo.

        // 4. Gọi Service để xử lý nghiệp vụ (Kiểm tra trùng Email, Hash Mật khẩu, Lưu CSDL)
        try {
            // Service xử lý toàn bộ nghiệp vụ và lưu vào DB
            User registeredUser = userService.registerUser(newLearner, password); 

            if (registeredUser != null) {
                // Đăng ký thành công, chuyển hướng đến trang Đăng nhập
                response.sendRedirect(request.getContextPath() + "/login?success=registered"); 
            } else {
                // Lỗi CSDL (Service trả về null)
                request.setAttribute("errorMessage", "Lỗi hệ thống: Không thể lưu dữ liệu.");
                doGet(request, response);
            }

        } catch (Exception e) {
            // Bắt lỗi nghiệp vụ từ Service (ví dụ: "Email đã được sử dụng")
            request.setAttribute("errorMessage", e.getMessage());
            
            // Giữ lại các giá trị đã nhập để người dùng không phải gõ lại
            request.setAttribute("enteredName", name);
            request.setAttribute("enteredEmail", email);
            
            doGet(request, response); // Quay lại form
        }
    }
}