package com.aesp.controller;

import com.aesp.service.UserService;
import com.aesp.pojo.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/admin/users") // URL gốc cho quản lý người dùng
public class AdminUserController {
    private final UserService userService;
    @Autowired
    public AdminUserController(UserService userService) {
        this.userService = userService;
    }
    // =======================================================
    // 1. GET: Hiển thị danh sách TẤT CẢ người dùng (Admin role requirement)
    // URL: /admin/users/list
    // =======================================================
    @GetMapping("/list")
    public String listAllUsers(Model model) {
        List<User> allUsers = userService.findAllUsers(); 
        model.addAttribute("users", allUsers);
        return "admin/user_list_table"; 
    }
    // =======================================================
    // 2. POST: Xử lý Kích hoạt/Vô hiệu hóa tài khoản (Enable/Disable Account)
    // URL: /admin/users/toggle-status
    // =======================================================
    @PostMapping("/toggle-status")
    public String toggleUserStatus(@RequestParam int userId, @RequestParam String currentStatus) {
        try {
            // Logic: Tìm user, đảo ngược trạng thái, và cập nhật
            userService.toggleUserStatus(userId, currentStatus);
            return "redirect:/admin/users/list"; 
        } catch (Exception e) {
            return "redirect:/admin/users/list?error=" + e.getMessage(); 
        }
    }
    // =======================================================
    // 3. GET: Hiển thị trang chỉnh sửa tài khoản (Tùy chọn)
    // =======================================================
    @GetMapping("/{userId}/edit")
    public String editUserForm(@PathVariable int userId, Model model) {
        User user = userService.findUserById(userId);
        model.addAttribute("user", user);
        return "admin/user_edit_form"; 
    }
}
