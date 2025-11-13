package com.aesp.gui;

import com.aesp.pojo.Admin;
import com.aesp.pojo.Learner;
import com.aesp.pojo.Mentor;
import com.aesp.pojo.User;
import com.aesp.service.UserService;
import java.util.Scanner;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

public class Apprun {
    public static void main(String[] args) {
        UserService userService = null;
        EntityManagerFactory emf = null;
        EntityManager em = null;
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        try {
            userService = new UserService();
            System.out.println("=============================================");
            System.out.println("  He thong huan luyen tieng anh (AESP)  ");
            System.out.println("=============================================");

            while (running) {
                System.out.println("\n--- MENU CHUC NANG  ---");
                System.out.println("1. Dang ky (Learner)");
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

        } catch (Exception e) {
            System.err.println("\nLoi chuong trinh: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // Đóng tài nguyên khi thoát
            System.out.println("\n--- Dong tai nguyen JPA ---");
            scanner.close();
            userService.closeRe1pository();
        }
    }

    // =======================================================
    // CHỨC NĂNG 1: ĐĂNG KÝ registerLearner handleRegistration
    // =======================================================
   private static void handleRegistration(Scanner scanner, UserService userService) {
    System.out.println("\n--- Dang ky (User) ---");

    try {
        System.out.print("   chon vai tro dang ky: "); // <<< THÊM INPUT NÀY 
        
        System.out.print("   Learner: ");
        
        System.out.print("   Mentor: ");
        
        System.out.print("  Admin: ");
        
        String roleChoise = scanner.nextLine();
        
        String role; 
        User newUser;

        switch (roleChoise) {
            case "1":
               role = "Learner";
               newUser = new Learner();
                break;
            case "2":
               role = "Mentor";
               newUser = new Mentor();
            break;
            case "3":
               role = "Admin";
               newUser = new Admin();
                break;
        
            default:
               System.out.println("loi chua vai tro khong hop le");
            return;
        }
        
        
        System.out.println("(Name)ho va ten day du = ");
        String regName = scanner.nextLine();

        System.out.println("Nhap thong tin email = ");
        String regemail = scanner.nextLine();

        System.out.println("Nhap thong tin password = ");
        String regpassword = scanner.nextLine();

        String regGoal = null;
        if(newUser instanceof Learner ){
            System.out.println("nhap muc tieu hoc");
            regGoal = scanner.nextLine();
            //ép kiểu lựa chọn sai
            ((Learner)newUser).setGoal(regGoal);
        }
    
    // --- CẤP PHÁT CÁC GIÁ TRỊ MẶC ĐỊNH ---
        newUser.setName(regGoal);
        newUser.setEmail(regemail);
        newUser.setStatus("Active");
        newUser.setCreatedAt(java.time.LocalDate.now());
        newUser.setUpdatedAT(java.time.LocalDate.now());
        // ------------------------------------
        
         
        // 5. GỌI SERVICE ĐĂNG KÝ CHUNG
        User registeredUser = userService.registerUser(newUser, regpassword);
        
        // 6. XỬ LÝ KẾT QUẢ
        if (registeredUser != null) {
            System.out.println("   [Thanh cong] Dang ky hoan tat!");
            System.out.println("   -> ID: " + registeredUser.getId() + ", Email: " + registeredUser.getEmail());
            System.out.println("   -> Ten: " + registeredUser.getName() + ", Vai tro: " + registeredUser.getRole()); // Dùng getRole
        } else {
            System.out.println("   [That bai] Dang ky khong thanh cong. Loi he thong CSDL.");
        }
    } catch (Exception e) {
        System.err.println("   [Loi] Dang ky that bai: " + e.getMessage());
    }
}
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