package com.aesp.pojo;

public class UserFactory implements IUserFactory {

    //.1
    @Override
    public Learner createLearner(String name, String mail,String pw){
        long id = System.currentTimeMillis();
        //đúng contrector Learner: 
        return new Learner(id, mail, name, "A1");       
    }

    @Override 
    public Mentor createMentor(String name, String mail, String pw) {
        long id = System.currentTimeMillis();
        //đúng contrector Mentor:
        return new Mentor(id, mail, name);              
    }

    
}