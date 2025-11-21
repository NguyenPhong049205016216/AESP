package com.aesp.repository;

import java.util.List;
import com.aesp.pojo.User;
import com.aesp.pojo.Admin;
import com.aesp.pojo.Mentor;
import com.aesp.pojo.Learner;
import java.util.Optional;
import org.springframework.stereotype.Repository;
@Repository
public interface IUserRepository {
    
    Optional<User> findById(Long id);
    
    List<User> findAll();

    List<User> getAllUsers();

    User saveUser(User user);

    Learner addLearner(Learner learner);

    Mentor addMentor(Mentor mentor);

    Admin addAdmin(Admin admin);

    User updateUser(User user);

    void deleteUser(Long id);

	Optional<User> findByEmail(String email);


} 
