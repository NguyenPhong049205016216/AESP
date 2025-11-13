package com.aesp.dao;

import java.util.*;
import jakarta.persistence.*;
import com.aesp.pojo.User;

import org.springframework.stereotype.Repository; // <<< THÊM: Annotation Spring
import org.springframework.transaction.annotation.Transactional; // <<< THÊM: Annotation Spring

@Repository 
public class Userdao {  

    @PersistenceContext
    private EntityManager em; 

    @Transactional
    public  boolean save(User user){ 
        try{
            em.persist(user); 
            return true; 
        }catch (Exception e ){
            e.printStackTrace();
            return false;                                                             
        }
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
    public User findById(long id) {
        return em.find(User.class, id);  
    }
    public List<User> findAll() {
        return em.createQuery("SELECT u FROM User u", User.class).getResultList();

    }
    
    public User findByEmail(String email){
        try{
            TypedQuery<User> query = em.createQuery("SELECT u FROM User u WHERE u.email = :emailParam",User.class);
            query.setParameter("emailParam", email); // SỬA LỖI THAM SỐ
            return query.getSingleResult();
        }catch(NoResultException e){
            return null;
        }/*catch(Exception e){
            e.printStackTrace();
            return null;
        } */
    }
}