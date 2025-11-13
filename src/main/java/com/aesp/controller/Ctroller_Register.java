package com.aesp.controller;

import com.aesp.service.UserService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.aesp.pojo.Learner;
import com.aesp.pojo.User;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Controller; 
import org.springframework.ui.Model; 
import org.springframework.web.bind.annotation.GetMapping; 
import org.springframework.web.bind.annotation.PostMapping; 
import org.springframework.web.bind.annotation.RequestMapping; 
import org.springframework.web.bind.annotation.RequestParam; 

@Controller
@RequestMapping("/")
// Đặt tên class rõ ràng: RegistrationServlet
public class Ctroller_Register  { 
    
    private UserService userService; 
    @Autowired
    public Ctroller_Register(UserService userService){
        this.userService = userService;
    }
    // =======================================================
    // PHƯƠNG THỨC GET: Hiển thị trang đăng ký
    // Tương đương với doGet()
    // =======================================================
    @GetMapping("/register")
    public String ShowRegisTrationFrom(){
        return "register";
    }
    // =======================================================
    // PHƯƠNG THỨC GET:  xữ lý dữ liệu
    // Tương đương với doGet()
    // =======================================================
    @PostMapping("/regester")
    public String registerUser(
        @RequestParam String name,
        @RequestParam String email,
        @RequestParam String password,
        @RequestParam("confirm-password") String confirmPassword,
        @RequestParam(required = false) String goal,
         Model model){
            // 1. Kiểm tra nghiệp vụ cơ bản (Không cần request.getParameter)
        if (!password.equals(confirmPassword)) {
            model.addAttribute("errorMessage", "Mật khẩu và Xác nhận mật khẩu không khớp.");
            model.addAttribute("enteredName", name); // Giữ lại data
            model.addAttribute("enteredEmail", email);
            return "register"; // Quay lại trang register.jsp
       
        }
        Learner newLearner = new Learner();
        newLearner.setName(name);
        newLearner.setEmail(email); 
        newLearner.setGoal(goal);
        try {
            // Service xử lý toàn bộ nghiệp vụ và lưu vào DB
            User registeredUser = userService.registerUser(newLearner, password); 

            if (registeredUser != null) {
                // Đăng ký thành công, chuyển hướng đến trang Đăng nhập
                return "redirect:/login?success=registered"; 
            } else {
                // Lỗi CSDL (Service trả về null)
                model.addAttribute("errorMessage", "Lỗi hệ thống: Không thể lưu dữ liệu.");
                return "register";
            }
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
            model.addAttribute("enteredName", name);
            model.addAttribute("enteredEmail", email);
            return "register";
        }

    }
}