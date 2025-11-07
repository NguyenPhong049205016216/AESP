package com.aesp.service;

import java.util.ArrayList;
import java.util.List;
import com.aesp.pojo.User;
import com.aesp.repository.UserRepository;
import java.time.LocalDate;
//xử lý nghiệp vụ -> repository || pojo
public class UserService{
    //UserService::::call repository
    private UserRepository userRepository;

    private List<User> users = new ArrayList<>();

    //UserService::::khỏi tạo cuntructor -> gọi theo thứ tự
    public UserService(){
        this.userRepository = new UserRepository();
    }
    //UserService::::nghiệp vụ cập nhập hồ sơ.->pojo||repository||dao
    public boolean updateProfile(User user, String newName, String newImail, String newPassword) {
        if (user == null) return false;

        user.setName(newName);
        user.setImail(newImail);
        user.setPassword(newPassword);
        user.setUpdatedAT(LocalDate.now());

        userRepository.updateUser(user);
        return true;
    }
    //UserServicenghiệp vụ đăng ký ->repository||dao||pojo
    public void register(User user) {
        users.add(user);
        /*/ kiểm tra email trùng
        if (userRepository.findByEmail(user.getImail()) != null) {
            return false; // đã tồn tạ
        }
        userRepository.save(user);
        return true;
        */
    }
    //UserService::::nghiệp vụ đăng nhập
    public User login(String email, String password) {
        User u = userRepository.findById(email.hashCode()).orElse(null);
        if (u != null && u.getPassword().equals(password)) {
            return u;
        }
        return null;
    }

    public List<User> getAllUser(){
        return users;
    }

}
