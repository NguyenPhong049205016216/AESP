package com.aesp.dao;
import java.util.*;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;

import com.aesp.pojo.User;
    //dống Solid tuân theo nguyên tắc.
public class Userdao {  
    //lệnh này dùng để đọc tham số cấu hình trong Pom.xml.
    private static EntityManager entityManager;
    private static EntityManagerFactory entityManagerFactory; 
    public Userdao(String persistenceName){
        entityManagerFactory = Persistence.createEntityManagerFactory(persistenceName);
        entityManager = entityManagerFactory.createEntityManager();
    }
    //dùng  Factory class entityManager
    public  boolean save (User user){ //lưu CN: đăng ký.đăng nhập.hồ sơ.
        try{
            entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            //thêm đói tượng vào CSDL persist..
            entityManager.persist(user);
            entityManager.getTransaction().commit();
            return true; 
        }catch (Exception e ){
            if (entityManager.getTransaction().isActive()){
                entityManager.getTransaction().rollback();
            }
            e.printStackTrace();
            return false;                                                             
        }finally{
            if (entityManager != null){
                entityManager.close();
            }
        }
            
    }
    //
    public boolean update(User user){ //cập nhập CN: Hồ sơ
    try{
            entityManager = entityManagerFactory.createEntityManager();
            //quảng lý trang conlection bắt đầu =  begin
            entityManager.getTransaction().begin();
            //updata vào CSDL dùng merge.
            entityManager.merge(user);
            //không sảy ra lỗi commit sác thực bản ghi suống CSDL.
            entityManager.getTransaction().commit();
            return true; 
        }catch (Exception e ){
            //lỗi rollback lại nếu sai số 
            if (entityManager.getTransaction().isActive()){
                entityManager.getTransaction().rollback();
            }
            e.printStackTrace();
            return false;
        }finally{
            if (entityManager != null){
                //đóng lại conlection.
                entityManager.close();
            }
        }

    }
    public boolean delete(long id){
        try{
            entityManager = entityManagerFactory.createEntityManager();
            //quảng lý trang conlection bắt đầu =  begin.
            entityManager.getTransaction().begin();
            //trước khi delere  kiểm tra sem id có trong CSDL không.
            User user = entityManager.find(User.class, id);
            if (user != null){
                //xóa vào CSDL dùng remove.
                    entityManager.remove(user);
                    //không sảy ra lỗi commit sác thực bản ghi suống CSDL.
                    entityManager.getTransaction().commit();
                    return true; 
                }else {
                return false;
            }
                
        }catch (Exception e ){
            //lỗi rollback lại nếu sai số.
            if (entityManager.getTransaction().isActive()){
                entityManager.getTransaction().rollback();
            }
            e.printStackTrace();
            return false;
        }finally{
            if (entityManager != null){
                //đóng lại conlection.
                entityManager.close();
            }
        }

    }
    //dùng cho cập nhập. xáo. xem chi tiếc
    public User findById(long id) {
        //tạo biến để kết nối csdl
        EntityManager em = null;
        try {
            //mở kết nối sớm đến cơ sở dử liệu
            em = entityManagerFactory.createEntityManager();
            //tìm đối tượng user có id tương ứng trong csdl
            return em.find(User.class, id);  
        //nếu có lỗi      
        } catch (IllegalArgumentException | PersistenceException ex) {
            ex.printStackTrace();                     
            return null;
        //luôn kết nối sau khi hoàn thành.
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }
    //dùng cho duyệt danh sách
    public List<User> findAll() {
        EntityManager em = null;
        try {
            em = entityManagerFactory.createEntityManager();
            return em.createQuery("SELECT u FROM User u", User.class).getResultList();
        } catch (IllegalArgumentException | PersistenceException ex) {
            ex.printStackTrace();
            return Collections.emptyList();
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }   
    }
    public User findByEmail(String email){
        EntityManager em = null;
        try{
            em = entityManagerFactory.createEntityManager();
            return em.createQuery("SELECT u FROM U", User.class).setParameter(email, em).getSingleResult();
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }finally{
            if (em != null && em.isOpen()){
                em.close();
            }
        }
             
    }

}

