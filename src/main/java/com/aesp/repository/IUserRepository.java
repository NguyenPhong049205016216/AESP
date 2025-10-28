package com.aesp.repository;

import java.util.List;
import com.aesp.pojo.User;
public interface IUserRepository {
    //khai bóa theo tạo nghiệp vụ memory tạm
    void addUser(User user); //thêm user
    List <User> getAllUser(); //lấy toàn bộ user

    //khai báo CRUD theo ổ cứng
    //lt data user->hồ sơ 
    public boolean save(User user);
    //lt data update User
    public boolean update(User user);
     
    //lt data kết nối email: xai nếu chưa khỏi tạo trong Userdao
    public User findByEmail(String email);
    
} 
