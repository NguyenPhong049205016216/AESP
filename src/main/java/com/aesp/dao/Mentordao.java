package com.aesp.dao;

import com.aesp.pojo.Mentor;
public class Mentordao extends Userdao {
    //call  contructor của lớp cha
    public Mentordao(String persistenceName){
        super(persistenceName);
    }
    
    public boolean saveMentor(Mentor mentor){
        return save(mentor);
    }

    public boolean updateMentor(Mentor mentor){
        return update(mentor);
    }

    public boolean deleteMentor(long id){
        return delete(id);
    }
    
}
