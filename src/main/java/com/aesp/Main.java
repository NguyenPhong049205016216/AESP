package com.aesp;

import com.aesp.pojo.User;
import javax.persistence.EntityTransaction;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class Main {
      
    public static void main(String[] args) {
        EntityManagerFactory emf = null;
        EntityManager em = null;

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
    }
    
}