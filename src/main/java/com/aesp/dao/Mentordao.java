package com.aesp.dao;

import javax.persistence.*;
import com.aesp.pojo.Mentor;
public class Mentordao extends Userdao {
    public Mentordao(String persistenceName){
        super(persistenceName);
    }
    public boolean saveMentor(Mentor mentor){
        return save(mentor); 
    }

    public boolean updataMentor(Mentor mentor){
        return update(mentor);
    }

    public boolean deleteMentor(long id){
        return delete(id);
    }
    
}
