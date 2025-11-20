package com.aesp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class PackageController {
    // 1. GET: Xử lý yêu cầu /packages
    @GetMapping("/packages")
    public String showPricingPage() {
        // Trả về tên file HTML (Spring sẽ tìm trong /static/packages.html)
        return "packages.html"; 
    }
    // Tùy chọn: Thêm Controller POST cho quá trình mua gói học (purchase)
}
