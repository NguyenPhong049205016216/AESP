package com.aesp.pojo;

public interface IUserFactory{
    //methor phân loại người dùng:.1
    public Learner createLearner(String name, String mail, String pw);
    public Mentor createMentor(String name, String mail, String pw); 
}
