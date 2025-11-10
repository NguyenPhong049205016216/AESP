package com.aesp.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.aesp.pojo.Learner;
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
        user.setEmail(newImail);
        user.setPassword(newPassword);
        user.setUpdatedAT(LocalDate.now());

        userRepository.updateUser(user);
        return true;
    }
    //UserServicenghiệp vụ đăng ký ->repository||dao||pojo  
    public User registerLearner(Learner learner, String rawPassword) throws Exception {
        
        // 1. Kiểm tra email trùng
        if (userRepository.findByEmail(learner.getEmail()).isPresent()) {
            throw new Exception("Email đã được sử dụng. Vui lòng chọn email khác.");
        }
        
        // 2. Hash mật khẩu (Bước bảo mật quan trọng)
        String hashedPassword = PasswordUtil.hashPassword(rawPassword);
        learner.setPassword(hashedPassword);
        
        // 3. Set các giá trị nghiệp vụ
        learner.setRole("LEARNER");
        learner.setStatus("ACTIVE");
        learner.setCreatedAt(new Date());
        learner.setUpdateAt(new Date());
        
        // 4. Lưu vào CSDL
        return userRepository.addLearner(learner);
    }
    //UserService::::nghiệp vụ đăng nhập
    public User login(String email, String password) {
        
        // 1. Tìm User theo Email (đúng cách)
        Optional<User> userOptional = userRepository.findByEmail(email);
        
        if (userOptional.isEmpty()) {
            return null; // Không tìm thấy email
        }
        
        User user = userOptional.get();
        
        // 2. So sánh mật khẩu (So sánh mật khẩu thô với Mật khẩu đã Hash trong CSDL)
        if (PasswordUtil.checkPassword(password, user.getPassword())) {
            return user; // Đăng nhập thành công
        } else {
            return null; // Sai mật khẩu
        }
    }
    

    public List<User> getAllUser(){
        return users;
    }
    public User Ctroller_Register(Learner newLearner, String regPassword) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'Ctroller_Register'");
    }
    public void closeRepository() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'closeRepository'");
    }
    public void closeRe1pository() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'closeRe1pository'");
    }

}
