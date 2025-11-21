package com.aesp.repository;
import java.util.List;
import java.util.Optional;
import com.aesp.pojo.Admin;
import com.aesp.pojo.Learner;
import com.aesp.pojo.Mentor;
import com.aesp.pojo.User;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository; 
@Repository
public class UserRepository implements IUserRepository {

    private final JpaUserRepository jpaUserRepository;
    @Autowired
    public UserRepository(JpaUserRepository jpaRepository){
        this.jpaUserRepository = jpaRepository;
    }
    // ===================================================================
    // CHỨC NĂNG ĐĂNG KÝ (Registration)
    // ===================================================================
    @Override
    @Transactional
    public User saveUser(User user) {
        return jpaUserRepository.save(user);
    }
    @Override
    @Transactional
    public Mentor addMentor(Mentor mentor) {
        return (Mentor) jpaUserRepository.save(mentor);
    }
    @Override
    @Transactional
    public Admin addAdmin(Admin admin) {
        return (Admin) jpaUserRepository.save(admin);
    }
    @Override
    @Transactional
    public Learner addLearner(Learner learner) {
        return (Learner) jpaUserRepository.save(learner);
    }

    // ===================================================================
    // CHỨC NĂNG ĐĂNG NHẬP (Login - Tìm theo Email)
    // ===================================================================
    @Override
    public Optional<User> findByEmail(String email) {
        return jpaUserRepository.findByEmail(email);
    }
    // ===================================================================
    // Triển khai các phương thức còn lại
    // ===================================================================
    @Override
    public Optional<User> findById(Long id) {
        return jpaUserRepository.findById((long)id);
    }
    @Override
    public List<User> findAll() {
        return jpaUserRepository.findAll();
    }
    @Override
    public List<User> getAllUsers() {
        return jpaUserRepository.findAll();
    }
    // ===================================================================
    // Triển khai các phương thức cập nhập, xóa, sữa.
    // ===================================================================
    @Override
    @Transactional
    public User updateUser(User user) {
        return jpaUserRepository.save(user);
    }
    @Override
    @Transactional
    public void deleteUser(Long id) {
        jpaUserRepository.deleteById((long)id);
    }
    
    
}