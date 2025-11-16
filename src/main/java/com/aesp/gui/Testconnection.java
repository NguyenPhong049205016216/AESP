package com.aesp.gui;

/*import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Profile;

import com.aesp.pojo.Admin;
import com.aesp.pojo.Learner;
import com.aesp.pojo.Mentor;
import com.aesp.pojo.User;
import com.aesp.service.UserService;

import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;

@SpringBootApplication
@ComponentScan(basePackages = "com.aesp")
//@Profile("cli")
public class Testconnection{
    @Autowired
    private UserService userService; 
    public static void main(String[] args) {
        SpringApplication.run(Testconnection.class, args);
    }
    /*@Override
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

        switch (roleChoise) {
            case "1":
               role = "LEARNER";
               newUser = new Learner();
               break;
            case "2":
               role = "MENTOR";
               newUser = new Mentor();
               break; 
            case "3":
               role = "ADMIN";
               newUser = new Admin();
               break;
            default:
               System.out.println("Loi: Lua chon vai tro khong hop le.");
            return;
        }
        System.out.print("(Name)ho va ten day du = ");
        String regName = scanner.nextLine();

        System.out.print("Nhap thong tin email = "); 
        String regemail = scanner.nextLine();

        System.out.print("Nhap thong tin password = "); 
        String regpassword = scanner.nextLine();

        String regGoal = null;
        if(newUser instanceof Learner ){
            System.out.print("Nhap muc tieu hoc = "); 
            regGoal = scanner.nextLine();
            ((Learner)newUser).setGoal(regGoal);
        }
        newUser.setName(regName); 
        newUser.setEmail(regemail);
        User registeredUser = userService.registerUser(newUser, regpassword);
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
       */