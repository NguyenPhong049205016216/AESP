package com.aesp.repository;
import java.util.List;
import com.aesp.dao.Learnerdao;
public class LearnerRepository implements ILearnerRepository{
    private Learnerdao learnerdao;

    public LearnerRepository(){
        this.learnerdao = new Learnerdao();
    }
}
