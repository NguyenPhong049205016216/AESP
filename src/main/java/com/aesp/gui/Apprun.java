package com.aesp.gui;

import com.aesp.pojo.Learner;
import com.aesp.pojo.Mentor;
import com.aesp.pojo.Admin;
import com.aesp.pojo.User;
import com.aesp.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import org.springframework.boot.autoconfigure.domain.EntityScan; // <<< THÊM IMPORT
import org.springframework.data.jpa.repository.config.EnableJpaRepositories; 

import java.util.Scanner;

// =========================================================================================
// LỚP KHỞI ĐỘNG CHÍNH CỦA SPRING BOOT TEST (THAY THẾ main() CŨ)
// =========================================================================================
@SpringBootApplication 
@ComponentScan(basePackages = "com.aesp") 
@EnableJpaRepositories(basePackages = "com.aesp.repository") // <<< BẮT BUỘC: Nơi Repository Interface của bạn nằm
@EntityScan(basePackages = "com.aesp.pojo") 
public class Apprun implements CommandLineRunner { 

    @Autowired
    private UserService userService; 
    public static void main(String[] args) {
        // KHỞI ĐỘNG SPRING BOOT CONTEXT (Để Spring quản lý các @Service, @Repository)
        SpringApplication.run(Apprun.class, args);
    }
    // HÀM CHÍNH CHẠY SAU KHI SPRING KHỞI TẠO
    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        
        System.out.println("=============================================");
        System.out.println("  He thong huan luyen tieng anh (AESP) - CLI");
        System.out.println("=============================================");
        
        while (running) {
            System.out.println("\n--- MENU CHUC NANG  ---");
            System.out.println("1. Dang ky (User)");
            System.out.println("2. Dang nhap");
            System.out.println("0. Thoat");
            System.out.print("Chon chuc nang (0-2): ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    handleRegistration(scanner, userService); 
                    break;
                case "2":
                    handleLogin(scanner, userService);
                    break;
                case "0":
                    running = false;
                    System.out.println("Thoat chuong trinh tam.biet!");
                    break;
                default:
                    System.out.println("lua chon khong hop le, vui long chon lai.");
            }
        }
        scanner.close();
        System.out.println("--- Dong tai nguyen JPA ---"); 
    }
    
    // =======================================================
    // CHỨC NĂNG 1: ĐĂNG KÝ (Đã sửa lỗi Logic)
    // =======================================================
   private static void handleRegistration(Scanner scanner, UserService userService) {
    System.out.println("\n--- Dang ky (User) ---");

    try {
        System.out.println("Vui long chon vai tro dang ky:");
        System.out.println("1. Learner");
        System.out.println("2. Mentor");
        System.out.println("3. Admin");
        System.out.print("Nhap lua chon (1/2/3): "); // HƯỚNG DẪN NHẬP SỐ
        String roleChoise = scanner.nextLine();
        
        String role; 
        User newUser;

        // 1. SWITCH CASE VÀ KHỞI TẠO ĐỐI TƯỢNG
        switch (roleChoise) {
            case "1":
               role = "LEARNER";
               newUser = new Learner();
               break;
            case "2":
               role = "MENTOR";
               newUser = new Mentor();
               break; // <<< LỖI CŨ ĐÃ SỬA: Phải có break
            case "3":
               role = "ADMIN";
               newUser = new Admin();
               break;
            default:
               System.out.println("Loi: Lua chon vai tro khong hop le.");
            return;
        }
        
        // 2. NHẬP THÔNG TIN CẦN THIẾT
        System.out.print("(Name)ho va ten day du = "); // Dùng print
        String regName = scanner.nextLine();

        System.out.print("Nhap thong tin email = "); // Dùng print
        String regemail = scanner.nextLine();

        System.out.print("Nhap thong tin password = "); // Dùng print
        String regpassword = scanner.nextLine();

        String regGoal = null;
        if(newUser instanceof Learner ){
            System.out.print("Nhap muc tieu hoc = "); // Dùng print
            regGoal = scanner.nextLine();
            ((Learner)newUser).setGoal(regGoal);
        }
    
        // 3. CẤP PHÁT VÀ SET GIÁ TRỊ (Service sẽ xử lý Role/Status/Time)
        newUser.setName(regName); // <<< SỬA LỖI LOGIC CŨ: Không dùng regGoal nữa
        newUser.setEmail(regemail);
        
        // 4. GỌI SERVICE ĐĂNG KÝ CHUNG
        // Service sẽ tự set Role, Status, Hash Password, và Thời gian
        User registeredUser = userService.registerUser(newUser, regpassword);
        
        // 5. XỬ LÝ KẾT QUẢ
        if (registeredUser != null) {
            System.out.println("   [Thanh cong] Dang ky hoan tat!");
            System.out.println("   -> ID: " + registeredUser.getId() + ", Email: " + registeredUser.getEmail());
            System.out.println("   -> Ten: " + registeredUser.getName() + ", Vai tro: " + registeredUser.getRole());
        } else {
            System.out.println("   [That bai] Dang ky khong thanh cong. Lỗi hệ thống CSDL.");
        }
    } catch (Exception e) {
        System.err.println("   [Loi] Dang ky that bai: " + e.getMessage());
    }
}
    // =======================================================
    // CHỨC NĂNG 2: ĐĂNG NHẬP (Giữ nguyên logic cũ)
    // =======================================================
    private static void handleLogin(Scanner scanner, UserService userService) {
        System.out.println("\n--- Dang nhap ---");
        System.out.print("   Nhap Email: ");
        String loginEmail = scanner.nextLine();
        
        System.out.print("   Nhap mat khau: ");
        String loginPassword = scanner.nextLine();
        
        User loggedInUser = userService.login(loginEmail, loginPassword);
        
        if (loggedInUser != null) {
            System.out.println("   [Thanh cong] Dang nhap thanh cong!");
            System.out.println("   -> Chao mung " + loggedInUser.getEmail() + ", Vai Tro: " + loggedInUser.getRole());
        } else {
            System.out.println("   [That bai] Dang nhap khong thanh cong! Sai Email hoac mat khau.");
        }
    }
}