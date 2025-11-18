package com.aesp.controller;

import com.aesp.service.UserService;
import com.aesp.pojo.Learner;
import com.aesp.pojo.Mentor;
import com.aesp.pojo.Admin;
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
    
    @Autowired 
    public Ctroller_Register(UserService userService){
        this.userService = userService;
    }
    // =======================================================
    // PHƯƠNG THỨC GET: Hiển thị trang đăng ký
    // =======================================================
    @GetMapping("/register")
    public String ShowRegisTrationFrom(){
        return "register.html";
    }
    // =======================================================
    // PHƯƠNG THỨC POST: Xử lý dữ liệu
    // =======================================================
    @PostMapping("/register") 
    public String registerUser(
        @RequestParam String name,
        @RequestParam String email,
        @RequestParam String password,
        @RequestParam("confirm-password") String confirmPassword,
        @RequestParam String role,
        @RequestParam(required = false) String goal,

         Model model){
        if (!password.equals(confirmPassword)) {
            model.addAttribute("errorMessage", "Mật khẩu và Xác nhận mật khẩu không khớp.");
            model.addAttribute("enteredName", name); 
            model.addAttribute("enteredEmail", email);
            return "redirect.html"; 
        }
        User newUser = null;
        switch (role.toUpperCase()) {
            case "MEMTOR":
                newUser = new Mentor();
                break;
            case "ADMIN":
                newUser = new Admin();
                break;
            case "LEARNER":
                newUser = new Learner();
                break;
        
            default:
                Learner learner = new Learner();
                learner.setGoal(goal);
                learner = learner;
                break;
        }
        newUser.setName(name);
        newUser.setEmail(email);  
        try {
            User registeredUser = userService.registerUser(newUser, password); 
            String redirectUrl = "";
            if (registeredUser instanceof Learner){
                redirectUrl = "/learner.html";
            }else if(registeredUser instanceof Mentor){
                redirectUrl = "/Mentor.html";
            }else if(registeredUser instanceof Admin){
                redirectUrl = "/Admin.html";
            }else{
                redirectUrl = "/index.html";
            }
            return "redirect:" + redirectUrl;
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
            model.addAttribute("enteredName", name);
            model.addAttribute("enteredEmail", email);
            return "redirect.html";
        }
    }
}