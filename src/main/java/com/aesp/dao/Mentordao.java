package com.aesp.dao;


import com.aesp.pojo.Mentor;
import org.springframework.stereotype.Repository;
@Repository
public class Mentordao extends Userdao {
    
    public boolean saveMentor(Mentor mentor){
        return super.save(mentor); 
    }

    public boolean updataMentor(Mentor mentor){
        return super.update(mentor);
    }

    public boolean deleteMentor(long id){
        return super.delete(id);
    }
    
}
