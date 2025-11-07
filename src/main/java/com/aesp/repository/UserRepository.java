package com.aesp.repository;
import java.util.List;
import java.util.Optional;
import com.aesp.dao.Userdao;
import com.aesp.pojo.Admin;
import com.aesp.pojo.Learner;
import com.aesp.pojo.Mentor;
import com.aesp.pojo.User;
//không chứa dữ liệu, nhiệm vụ nó là  || gọi call csdl thật 
public class UserRepository implements IUserRepository{
    //không nên khai báo Userdao userdao = new Userdao();
    
    private Userdao userdao;

    public UserRepository(){
        try{
        userdao = new Userdao("JPAs");
    }catch(Exception e){
        System.err.println("Lỗi khởi tạo UserRepository"+ e.getMessage());
        e.printStackTrace();
    }
 }
    @Override
    public Optional<User> findById(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }

    @Override
    public List<User> findAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }

    @Override
    public Learner addLearner(Learner learner) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addLearner'");
    }

    @Override
    public Mentor addMentor(Mentor mentor) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addMentor'");
    }

    @Override
    public Admin addAdmin(Admin admin) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addAdmin'");
    }

    @Override
    public User updateUser(User user) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateUser'");
    }

    @Override
    public void deleteUser(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteUser'");
    }

    @Override
    public List<User> getAllUsers() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllUsers'");
    }


}

