package com.aesp.controller;

import com.aesp.service.UserService;
import com.aesp.pojo.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/admin/users") // URL gốc cho quản lý người dùng
public class AdminUserController {
    private final UserService userService;

    @Autowired
    public AdminUserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/list")
    public String listAllUsers(Model model) {
        List<User> allUsers = userService.findAllUsers();
        model.addAttribute("users", allUsers);
        return "admin/user_list_table";
    }//hiển thị tất cả người dùng 

    @GetMapping("/toggle-status")
    public String toggleUserStatus(@RequestParam Long userId,
            @RequestParam String currentStatus, RedirectAttributes redirectAttributes) {
        try {
        userService.toggleUserStatus(userId, currentStatus);
        return "redirect:/admin/users/list"; 
        
    } catch (Exception e) {
        redirectAttributes.addFlashAttribute("errorMessage", "Lỗi: " + e.getMessage());
        return "redirect:/admin/users/list"; 
    }
    }//sữ lý kích hoạt, vô hiệu hóa tài khoản 

    @GetMapping("/{userId}/edit")
    public String editUserForm(@PathVariable Long userId, Model model) {
        User user = userService.findUserById(userId);
        if (user == null) {
            return "redirect:/admin/users/list?error=UserNotFound";
        }
        model.addAttribute("user", user);
        return "admin/user_edit_from";
    }//hiển thị chỉnh sữa tài khoản 

    
    @GetMapping("/{userId}/delete")
    public String deleteUser(@PathVariable Long userId) {
        try {
            userService.deleteUsert(userId);
            return "redirect:/admin/users/list?message=UserDeleted";
        } catch (Exception e) {
            return "redirect:/admin/users/list?error=" + e.getMessage();
        }
    } // xóa tài khoản 

    @PostMapping("/update")
    public String updateUser(@RequestParam Long id, @RequestParam String email,
            @RequestParam(required = false) String password, Model model) {
        try {
            User userToUpdate = userService.findUserById(id);
            if (userToUpdate == null) {
                return "redirect:/admin/users/list?error=UserNotFound";
            }
            userService.updateProfile(userToUpdate, userToUpdate.getName(), email, password);
            return "redirect:/admin/users/" + id + "/edit?success=updated";

        } catch (Exception e) {
            // Thất bại: Chuyển hướng về trang list với thông báo lỗi
            return "redirect:/admin/users/list?error=" + e.getMessage();
        }
    }// save thay đổi
}
