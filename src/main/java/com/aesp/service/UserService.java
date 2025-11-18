package com.aesp.service;

import java.util.ArrayList;
import java.util.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.aesp.pojo.Admin;
import com.aesp.pojo.Learner;
import com.aesp.pojo.Mentor;
import com.aesp.pojo.User;
import com.aesp.repository.IUserRepository;
import com.aesp.service.PasswordUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
// xử lý nghiệp vụ -> repository || pojo
// thay IUserRepository
public class UserService {
    private final IUserRepository userRepository;

    private List<User> users = new ArrayList<>();

    @Autowired

    public UserService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // ===================================================================
    // UserService::::nghiệp vụ cập nhập hồ sơ.->pojo||repository||dao
    // ===================================================================
    @Transactional
    public boolean updateProfile(User user, String newName, String newImail, String newPassword) {
        if (user == null)
            return false;

        user.setName(newName);
        user.setEmail(newImail);

        if (newPassword != null && !newPassword.isEmpty()) {
            user.setPassword(PasswordUtil.hashPassword(newPassword));
        }
        user.setUpdatedAT(LocalDate.now());
        userRepository.updateUser(user);
        return true;
    }

    // ===================================================================
    // UserServicenghiệp vụ đăng ký ->repository||dao||pojo
    // ===================================================================
    public User registerUser(User newUser, String rawPassword)
            throws Exception {

        if (userRepository.findByEmail(newUser.getEmail()).isPresent()) {
            throw new Exception("Email đã được sử dụng. Vui lòng chọn email khác.");
        }

        String hashedPassword = PasswordUtil.hashPassword(rawPassword);
        newUser.setPassword(hashedPassword);

        if (newUser instanceof Learner) {
            newUser.setRole("Learner");
        } else if (newUser instanceof Mentor) {
            newUser.setRole("Mentor");
        } else if (newUser instanceof Admin) {
            newUser.setRole("Learner");
        } else {
            newUser.setRole("UNKNOWN");
        }

        newUser.setStatus("ACTIVE");
        newUser.setCreatedAt(LocalDate.now());
        newUser.setCreatedAt(LocalDate.now());

        return userRepository.saveUser(newUser);
    }

    // ===================================================================
    // UserService::::nghiệp vụ đăng nhập
    // ===================================================================
    public User login(String email, String password) {

        Optional<User> userOptional = userRepository.findByEmail(email);

        if (userOptional.isEmpty()) {
            return null;
        }

        User user = userOptional.get();

        if (PasswordUtil.checkPassword(password, user.getPassword())) {
            return user;
        } else {
            return null;
        }
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    // ===================================================================
    // UserService:::Nghiệp vụ quảng lý tài khoản
    // ===================================================================
    // Hàm cần bổ sung cho AdminUserController (Thao tác DB cần @Transactional)
    @Transactional
    public void toggleUserStatus(int userId, String currentStatus) throws Exception {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new Exception("Tài khoản không tồn tại."));

        // Logic đảo ngược trạng thái
        String newStatus = "ACTIVE".equalsIgnoreCase(currentStatus) ? "DISABLED" : "ACTIVE";

        user.setStatus(newStatus);
        userRepository.saveUser(user); 
    }

    public User findUserById(int userId) {
        return userRepository.findById(userId).orElse(null);
    }

}
