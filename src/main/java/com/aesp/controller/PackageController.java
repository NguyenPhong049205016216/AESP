package com.aesp.controller;

import com.aesp.service.PackageService;
import com.aesp.pojo.Packages;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
@Controller
@RequestMapping("/admin/packages")
public class PackageController {
    private final PackageService packageService;
    @Autowired
    public PackageController(PackageService packageService) {
        this.packageService = packageService;
    }
    // =======================================================
    // 1. GET: Hiển thị danh sách Gói Dịch vụ (Cho View)
    // URL: /admin/packages/list
    // =======================================================
    @GetMapping("/list")
    public String listAllPackages(Model model) {
        model.addAttribute("packages", packageService.findAllPackages());
        return "admin/admin_packages_list"; // <<< Trả về View HTML
    }
    // =======================================================
    // 2. GET: Hiển thị Form Thêm/Sửa
    // URL: /admin/packages/new hoặc /admin/packages/{id}/edit
    // =======================================================
    @GetMapping({"/new", "/{id}/edit"})
    public String showPackageForm(@PathVariable(required = false) Long id, Model model) {
        Packages pkg;
        if (id == null) {
            pkg = new Packages();
        } else {
            try {
                pkg = packageService.getPackageById(id);
            } catch (Exception e) {
                pkg = new Packages();
                model.addAttribute("errorMessage", e.getMessage());
            }
        }
        
        model.addAttribute("package", pkg);
        return "admin/package_form"; // Trả về file form chung
    }
    // =======================================================
    // 3. POST: Xử lý Lưu (Thêm mới hoặc Cập nhật)
    // URL: /admin/packages/save
    // =======================================================
    @PostMapping("/save")
    public String savePackage(Packages pkg, RedirectAttributes redirectAttributes) {
        try {
            packageService.saveOrUpdatePackage(pkg);
            redirectAttributes.addFlashAttribute("message", "Lưu gói dịch vụ thành công!");
            
            return "redirect:/admin/packages/list"; 
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            // Quay lại form nếu có lỗi
            return "redirect:/admin/packages/new"; 
        }
    }
    // =======================================================
    // 4. GET: Xử lý Xóa (Thường dùng GET cho Dev/Admin đơn giản)
    // URL: /admin/packages/{id}/delete
    // =======================================================
    @GetMapping("/{id}/delete")
    public String deletePackage(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            packageService.deletePackage(id);
            redirectAttributes.addFlashAttribute("message", "Xóa gói dịch vụ thành công!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }
        return "redirect:/admin/packages/list";
    }
}
