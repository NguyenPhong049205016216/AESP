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
    public String toggleUserStatus(@RequestParam Long userId, @RequestParam String currentStatus) {
        try {
            // Logic: Tìm user, đảo ngược trạng thái, và cập nhật
            userService.toggleUserStatus(userId, currentStatus);
            return "redirect:/aesp/admin/users/list"; 
        } catch (Exception e) {
            return "redirect:/admin/users/list?error=" + e.getMessage(); 
        }
    }
    // =======================================================
    // 3. GET: Hiển thị trang chỉnh sửa tài khoản (Tùy chọn)
    // =======================================================
    @GetMapping("/{userId}/edit")
    public String editUserForm(@PathVariable Long userId, Model model) {
        User user = userService.findUserById(userId);
        if (user == null) {
            // Xử lý không tìm thấy user
            return "redirect:/aesp/admin/users/list?error=UserNotFound";
        }
        model.addAttribute("user", user);
        return "admin/user_edit_from"; 
    }
    // =======================================================
    // 4. GET: Xóa Tài khoản (Tạm thời - Dùng GET cho đơn giản)
    // URL: /admin/users/{userId}/delete
    // =======================================================
    // Lưu ý: Trong môi trường thực tế nên dùng POST/DELETE API
    @GetMapping("/{userId}/delete") 
    public String deleteUser(@PathVariable Long userId) {
        try {
            userService.deleteUsert(userId); // Giả định UserService có hàm deleteUser
            return "redirect:/aesp/admin/users/list?message=UserDeleted";
        } catch (Exception e) {
            return "redirect:/admin/users/list?error=" + e.getMessage();
        }
    }
    // =======================================================
    // 5. POST: Xử lý Lưu Thay Đổi (Update)
    // URL: /admin/users/update
    // =======================================================
    @PostMapping("/update") 
    public String updateUser(@RequestParam Long id, 
        @RequestParam String email,
        @RequestParam(required = false) String password,Model model) 
    {
        try {
            User userToUpdate = userService.findUserById(id);
            if (userToUpdate == null) {
                 return "redirect:/aesp/admin/users/list?error=UserNotFound";
            }
            userService.updateProfile(userToUpdate, userToUpdate.getName(), email, password);
            return "redirect:/aesp/admin/users/" + id + "/edit?success=updated"; 
            
        } catch (Exception e) {
            // Thất bại: Chuyển hướng về trang list với thông báo lỗi
            return "redirect:/aesp/admin/users/list?error=" + e.getMessage();
        }
    }

}
