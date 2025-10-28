package com.aesp.repository;
import java.util.ArrayList;
import java.util.List;
import com.aesp.dao.Userdao;
import com.aesp.pojo.User;
//chứa data dã lập || call csdl thật
public class UserRepository implements IUserRepository {

    //UserRepository::::call Userdao để để call ->csdl
    //UserRepository::::all lệnh này run được khi có csdl phần cứng rồi.
    private Userdao userdao;

    public UserRepository(){
        this.userdao = new Userdao("JPAs");
    }
    
    public List<User> user = new ArrayList<>();
    //thêm người dùng->dao
    @Override
    public boolean save(User user){
        return userdao.save(user);
    }
    //cập nhập người dùng->dao
    @Override
    public boolean update(User user){
        return userdao.update(user);
    }

    //tìm người dùng theo email.->dao
    @Override
    public User findByEmail(String email){
        return userdao.findByEmail(email);
    }
    @Override
    public void addUser(User user) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addUser'");
    }
    @Override
    public List<User> getAllUser() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllUser'");
    }
}

