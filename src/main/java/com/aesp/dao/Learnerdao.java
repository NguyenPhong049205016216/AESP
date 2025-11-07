package com.aesp.dao;

import com.aesp.pojo.Learner;
public class Learnerdao extends Userdao {
    //tái sử dụng lại cấu hình bằng hình thức kế thừa.
    //do Learner kế thừa User nên Learnerdao củng kế thừa Userdao.

    //gọi lại Cuntructor của lớp cha.
    public Learnerdao (String persistenceName){
        super(persistenceName);
    }

    //hàm lưu learner // do có yếu tố kế thừa.
    public boolean savelearner(Learner learner){
        return save(learner);
    }

    public boolean updatalearner(Learner learner){
        return update(learner);
    }

    public boolean deletelearner(long id){
        return delete(id);
    }
    

    //Learnerdao::::create hoạc 
    
}
