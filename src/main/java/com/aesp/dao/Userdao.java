package com.aesp.dao;
import java.util.*;
import javax.persistence.*;
import com.aesp.pojo.User;
    //dống Solid tuân theo nguyên tắc.
public abstract class Userdao {  
    //#lệnh này dùng để đọc tham số cấu hình trong Pom.xml.
    //private EntityManager entityManager;
    private static EntityManagerFactory entityManagerFactory; 
    /*public Userdao(String persistenceName){
        entityManagerFactory = Persistence.createEntityManagerFactory(persistenceName);
        entityManager = entityManagerFactory.createEntityManager();
    }
    */
    //constructor không tham số.
    public Userdao(){
    }
    //dùng static vì chỉ cần khởi tạo 1 lần.
    static {
        try{
            entityManagerFactory = Persistence.createEntityManagerFactory("JPAs");   
        }catch (Exception e){
            System.err.println("Lỗi khởi tạo EntityManagerFactory trong Userdao");
            e.printStackTrace();
            throw new ExceptionInInitializerError(e);
        }
    }
    //mỗi thao tác với CSDL sẽ tạo một EntityManager mới.
    public  boolean save(User user){ //lưu CN: đăng ký.đăng nhập.hồ sơ.
        EntityManager em = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try{
            transaction.begin();
            em.persist(user); //thêm đối tượng vào CSDL persist..X
            transaction.commit();
            //thêm đói tượng vào CSDL persist..X
            return true; 
        }catch (Exception e ){
            if (transaction != null && transaction.isActive()){
                transaction.rollback();
            }
            e.printStackTrace();
            return false;                                                             
        }finally{
            if(em != null){
                em.close();
            }
        }
    }
    // cập nhập người dùng
    public boolean update(User user){ //cập nhập CN: Hồ sơ
        EntityManager em = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try{
            transaction.begin();
            em.merge(user); //cập nhập dùng merge.
            transaction.commit();
            return true;
        }catch (Exception e ){
            if (transaction != null && transaction.isActive()){
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        }finally{
            if(em != null){
                em.close();
            }
        }
    }
    public boolean delete(long id){
        EntityManager em = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try{
            transaction.begin();
            User user = em.find(User.class, id);
            if (user != null){
                em.remove(user);
            }
            transaction.commit();
            return true;
        }catch (Exception e ){
            if (transaction != null && transaction.isActive()){
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        }finally{
            if(em != null){
                em.close();
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
    public static void closeFactory() {
        if (entityManagerFactory != null && entityManagerFactory.isOpen()) {
            entityManagerFactory.close();
        }
    }

}

