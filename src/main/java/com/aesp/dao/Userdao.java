package com.aesp.dao;

import java.util.*;
import jakarta.persistence.*;
import com.aesp.pojo.User;

import org.springframework.stereotype.Repository; // <<< THÊM: Annotation Spring
import org.springframework.transaction.annotation.Transactional; // <<< THÊM: Annotation Spring

// CHỈNH SỬA 1: Đổi thành @Repository để Spring quản lý
@Repository 
public class Userdao {  

    // CHỈNH SỬA 2: XÓA: private static EntityManagerFactory emf; 
    // CHỈNH SỬA 3: Inject EntityManager (Spring sẽ cung cấp nó)
    @PersistenceContext
    private EntityManager em; 

    // CHỈNH SỬA 4: XÓA CÁC CONSTRUCTOR VÀ KHỐI STATIC KHỞI TẠO THỦ CÔNG
    // XÓA: public Userdao(String persistenceName){ ... }
    // XÓA: static { ... }
    
    // CHỈNH SỬA 5: TẤT CẢ CÁC HÀM CẦN GIAO DỊCH PHẢI CÓ @Transactional
    @Transactional
    public  boolean save(User user){ 
        // XÓA: Khởi tạo Entity Manager thủ công
        // XÓA: EntityTransaction transaction = em.getTransaction();
        try{
            // Transaction được quản lý bởi Spring
            em.persist(user); 
            return true; 
        }catch (Exception e ){
            // XÓA: Logic rollback thủ công
            e.printStackTrace();
            return false;                                                             
        }
        // XÓA: Khối finally thủ công
    }

    @Transactional
    public boolean update(User user){ 
        try{
            em.merge(user); 
            return true;
        }catch (Exception e ){
            e.printStackTrace();
            return false;
        }
    }

    @Transactional
    public boolean delete(long id){
        try{
            User user = em.find(User.class, id);
            if (user != null){
                em.remove(user);
            }
            return true;
        }catch (Exception e ){
            e.printStackTrace();
            return false;
        }
    }
    
    // findById và findAll (Không cần @Transactional nếu chỉ là READ)
    public User findById(long id) {
        return em.find(User.class, id);  
        // XÓA: Logic try-catch-finally thủ công
    }

    public List<User> findAll() {
        return em.createQuery("SELECT u FROM User u", User.class).getResultList();
        // XÓA: Logic try-catch-finally thủ công
    }
    
    // Sửa lỗi cú pháp JPQL (Đã làm ở các bước trước)
    public User findByEmail(String email){
        try{
            // SỬA LỖI CÚ PHÁP Ở ĐÂY:
            TypedQuery<User> query = em.createQuery("SELECT u FROM User u WHERE u.email = :emailParam",User.class);
            query.setParameter("emailParam", email); // SỬA LỖI THAM SỐ
            return query.getSingleResult();
        }catch(NoResultException e){
            return null;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
    
    // XÓA: public static void closeFactory() { ... }
}