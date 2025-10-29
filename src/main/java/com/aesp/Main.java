package com.aesp;

import com.aesp.pojo.User;
import java.util.Scanner;
import com.aesp.service.UserService;

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
        //lệnh cấu hình kết nối vói sql
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
        }
    }
    
}