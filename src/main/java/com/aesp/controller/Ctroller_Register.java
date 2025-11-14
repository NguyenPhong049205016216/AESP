package com.aesp.controller;

import com.aesp.service.UserService;
import com.aesp.pojo.Learner;
import com.aesp.pojo.User;

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Controller; 
import org.springframework.ui.Model; 
import org.springframework.web.bind.annotation.GetMapping; 
import org.springframework.web.bind.annotation.PostMapping; 
import org.springframework.web.bind.annotation.RequestMapping; 
import org.springframework.web.bind.annotation.RequestParam; 

@Controller
@RequestMapping("/")
public class Ctroller_Register  { 
    
    private final UserService userService; 
    
    @Autowired // Injection Constructor
    public Ctroller_Register(UserService userService){
        this.userService = userService;
    }
    
    // =======================================================
    // PHƯƠNG THỨC GET: Hiển thị trang đăng ký
    // =======================================================
    @GetMapping("/register")
    public String ShowRegisTrationFrom(){
        return "register";
    }
    
    // =======================================================
    // PHƯƠNG THỨC POST: Xử lý dữ liệu
    // =======================================================
    @PostMapping("/register") // <<< SỬA LỖI CHÍNH TẢ
    public String registerUser(
        @RequestParam String name,
        @RequestParam String email,
        @RequestParam String password,
        @RequestParam("confirm-password") String confirmPassword,
        @RequestParam(required = false) String goal,
         Model model){
            // 1. Kiểm tra nghiệp vụ cơ bản
        if (!password.equals(confirmPassword)) {
            model.addAttribute("errorMessage", "Mật khẩu và Xác nhận mật khẩu không khớp.");
            model.addAttribute("enteredName", name); 
            model.addAttribute("enteredEmail", email);
            return "register"; 
        }
        
        Learner newLearner = new Learner();
        newLearner.setName(name);
        newLearner.setEmail(email); 
        newLearner.setGoal(goal);
        
        try {
            // Service xử lý Hash, Kiểm tra Trùng Email, Save
            userService.registerUser(newLearner, password); 

            // THÀNH CÔNG: Chuyển hướng đến trang Đăng nhập
            return "redirect:/login?success=registered"; 

        } catch (Exception e) {
            // LỖI: Bắt lỗi nghiệp vụ (Email trùng, v.v.)
            model.addAttribute("errorMessage", e.getMessage());
            model.addAttribute("enteredName", name);
            model.addAttribute("enteredEmail", email);
            return "register";
        }
    }
}