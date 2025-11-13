package com.aesp.repository;
import java.util.List;
import java.util.Optional;
import com.aesp.dao.Userdao;
import com.aesp.pojo.Admin;
import com.aesp.pojo.Learner;
import com.aesp.pojo.Mentor;
import com.aesp.pojo.User;

public class UserRepository implements IUserRepository{
    
    private Userdao userdao;

    public UserRepository(){
        try{
            userdao = new Userdao();
        }catch(Exception e){
            System.err.println("Lỗi khởi tạo UserRepository"+ e.getMessage());
            e.printStackTrace();
            // Ném lại để báo hiệu lỗi nghiêm trọng
            throw new RuntimeException("Cannot initialize Userdao", e); 
        }
    }
    
    // ===================================================================
    // CHỨC NĂNG ĐĂNG KÝ (Registration)
    // ===================================================================
    
    // Lưu ý: Trong một ứng dụng thực tế, nên hash mật khẩu trong tầng Service
    // hoặc ngay tại đây trước khi gọi userdao.save().
    
    @Override
    public User saveUser(User user) {
        // Learner là một User, nên có thể dùng save của Userdao
        // Logic nghiệp vụ: Cần kiểm tra email đã tồn tại chưa (có thể dùng findByEmail)
        
        boolean success = userdao.save(user);
        
        if (success) {
            // Trả về đối tượng đã lưu (có thể cần refresh để lấy ID nếu save không tự động)
            return user; 
        } else {
            // Ném Exception hoặc trả về null nếu lưu thất bại
            return null; 
        }
    }

    @Override
    public Mentor addMentor(Mentor mentor) {
        boolean success = userdao.save(mentor);
        if (success) {
            return mentor; 
        } else {
            return null; 
        }
    }

    @Override
    public Admin addAdmin(Admin admin) {
        boolean success = userdao.save(admin);
        if (success) {
            return admin; 
        } else {
            return null; 
        }
    }

    // ===================================================================
    // CHỨC NĂNG ĐĂNG NHẬP (Login - Tìm theo Email)
    // ===================================================================

    @Override
    public Optional<User> findByEmail(String email) {
        // Gọi phương thức findByEmail đã được sửa lỗi trong Userdao
        User user = userdao.findByEmail(email);
        
        // Dùng Optional.ofNullable để trả về một Optional, đây là cách làm tốt trong Java
        // để tránh lỗi NullPointerException khi không tìm thấy User
        return Optional.ofNullable(user);
    }


    // ===================================================================
    // Triển khai các phương thức còn lại
    // ===================================================================

    @Override
    public Optional<User> findById(int id) {
        // Chuyển đổi từ long ID sang int nếu cần, hoặc sửa findById trong DAO thành int/long
        User user = userdao.findById(id);
        return Optional.ofNullable(user);
    }

    @Override
    public List<User> findAll() {
        return userdao.findAll();
    }
    
    // ... Triển khai các phương thức khác tương tự ...
    
    @Override
    public User updateUser(User user) {
        boolean success = userdao.update(user);
        return success ? user : null; 
    }

    @Override
    public void deleteUser(int id) {
        userdao.delete(id);
    }

    @Override
    public List<User> getAllUsers() {
        return userdao.findAll();
    }

    public void closeFactory() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'closeFactory'");
    }

    @Override
    public Learner addLearner(Learner learner) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addLearner'");
    }
}