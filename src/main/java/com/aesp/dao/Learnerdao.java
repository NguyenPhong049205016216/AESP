package com.aesp.dao;

import com.aesp.pojo.Learner;
import org.springframework.stereotype.Repository;
public class Learnerdao extends Userdao {

    //hàm lưu learner // do có yếu tố kế thừa.
    public boolean savelearner(Learner learner){
        return super.save(learner);
    }

    public boolean updatalearner(Learner learner){
        return super.update(learner);
    }

    public boolean deletelearner(long id){
        return super.delete(id);
    }
    

    //Learnerdao::::create hoạc 
    
}
