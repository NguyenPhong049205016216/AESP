package com.aesp;

import com.aesp.pojo.Learner;
import com.aesp.pojo.User;
import com.aesp.repository.UserRepository;
import com.aesp.service.UserService;
import java.util.Scanner;

import java.util.Optional;

import javax.persistence.EntityTransaction;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.*;


public class Main {
      
    public static void main(String[] args) {
        EntityManagerFactory emf = null;
        EntityManager em = null;
        UserService userService = null;
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        /* 
        try {
            // Khởi tạo EntityManagerFactory.
            // JPA sẽ đọc file persistence.xml và cố gắng kết nối tới CSDL.
            // Nếu có lỗi ở đây, nghĩa là cấu hình kết nối của bạn bị sai.
            System.out.println("Dang  gang ket noi den database...");
            emf = Persistence.createEntityManagerFactory("JPAs"); 
            
            // Nếu dòng trên không báo lỗi, kết nối đã thành công!
            System.out.println("ket noi database duoc thiet lap! da thang cong.");

            em = emf.createEntityManager();

            // Bước 2: Thực hiện một giao dịch để kiểm tra ghi dữ liệu.
            EntityTransaction transaction = em.getTransaction();
            
            // Bắt đầu giao dịch
            transaction.begin();

            // Tạo một đối tượng User mới để lưu
            User newUser = new User();
            newUser.setName("Test User");
            newUser.setName("Test User From Main");
            newUser.setImail("test" + System.currentTimeMillis() + "@gmail.com"); // Email ngẫu nhiên để tránh trùng lặp
            newUser.setPassword("password123");
            newUser.setRole("LEARNER");
            newUser.setStatus("ACTIVE");

            // Lưu đối tượng vào CSDL
            em.persist(newUser);
            
            // Kết thúc giao dịch
            transaction.commit();

            System.out.println("test user saver sussecfuly Id: " + newUser.getId());
            
        } catch (Exception e) {
            // Nếu có bất kỳ lỗi nào xảy ra, in nó ra console.
            System.err.println("Da xay ra loi trong qua trinh kiem tra CSDL!");
            e.printStackTrace();
        } finally {
            // Bước 3: Luôn đóng các kết nối sau khi hoàn thành.
            if (em != null) {
                em.close();
            }
            if (emf != null) {
                emf.close();
                System.out.println("Dong ket noi.");
            }
        }
            */
        try {
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
    // CHỨC NĂNG 1: ĐĂNG KÝg
    // =======================================================
   private static void handleRegistration(Scanner scanner, UserService userService) {
    System.out.println("\n--- Dang ky (LEARNER) ---");
    try {
        System.out.print("   nhap ten day du (Name): "); // <<< THÊM INPUT NÀY
        String regName = scanner.nextLine(); 
        
        System.out.print("   Nhap email dang ky: ");
        String regEmail = scanner.nextLine();
        
        System.out.print("   Nhap mat khau: ");
        String regPassword = scanner.nextLine();
        
        System.out.print("   Nhap muc tieu hoc tap (Goal): ");
        String regGoal = scanner.nextLine();
        
        Learner newLearner = new Learner();
        newLearner.setEmail(regEmail);
        newLearner.setName(regName); // <<< SET NAME
        newLearner.setGoal(regGoal); 
        
        // --- CẤP PHÁT CÁC GIÁ TRỊ MẶC ĐỊNH ---
        newLearner.setRole("LEARNER");
        newLearner.setStatus("ACTIVE");
        newLearner.setCreatedAt(new java.util.Date());
        newLearner.setUpdateAt(new java.util.Date());
        // ------------------------------------
        
        // ... (phần gọi service và in kết quả)
        User registeredUser = userService.Ctroller_Register(newLearner, regPassword);
        
        if (registeredUser != null) {
            System.out.println("   [Thanh cong] Dang ky hoan tat!");
            System.out.println("   -> ID: " + registeredUser.getId() + ", Email: " + registeredUser.getEmail());
            System.out.println("   -> Ten: " + registeredUser.getName() + ", Trang thai: " + registeredUser.getStatus());
        } else {
            System.out.println("   [That bai] Dang ky khong thanh cong. Loi CSDL1.");
        }
    } catch (Exception e) {
        System.err.println("   [Loi] Dang ky loi: " + e.getMessage());
    }
}

    // =======================================================
    // CHỨC NĂNG 2: ĐĂNG NHẬP
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