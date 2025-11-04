package com.aesp;

import com.aesp.pojo.User;
import javax.persistence.EntityTransaction;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class Main {
    public static void main(String[] args) {
        /*UserService service = new UserService();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.print("\n========Menu==========");
            
            System.out.println("1: Dang ky");

            System.out.println("2: Dang nhap");

            System.out.println("3: dah sach user");

            System.out.println("thoat");

            System.out.println("chon");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice ) {
                case 1:
                    System.out.print("nhap email");
                    String email = sc.nextLine();
                    System.out.println("nhap password");
                    String password = sc.nextLine();
            
                case 2:
                    System.out.println("Danh sach nguoi dung");

                    break;
                case 3:

                    break;
            }
        }
            */
        /*//lệnh cấu hình kết nối vói sql
        try {
            // Đọc file persistence.xml -> tạo kết nối đến SQL Server
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPAs");
            EntityManager em = emf.createEntityManager();

            System.out.println("ket noi sql server thanh cong");
            em.close();
            emf.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("loi ket noi: " + e.getMessage());
        }*/    
        EntityManagerFactory emf = null;
        EntityManager em = null;

        try {
            // Bước 1: Khởi tạo EntityManagerFactory.
            // JPA sẽ đọc file persistence.xml và cố gắng kết nối tới CSDL.
            // Nếu có lỗi ở đây, nghĩa là cấu hình kết nối của bạn bị sai.
            System.out.println("Dang co gang ket noi den database...");
            emf = Persistence.createEntityManagerFactory("JPAs"); // "JPAs" phải khớp với tên trong persistence.xml
            
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
    }
    
}