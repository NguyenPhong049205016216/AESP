package com.aesp.pojo;

public class CleanApp {  
    public static void main(String [] args){
        Learner l = new Learner(1L, "alice@example.com", "Alice", "A2");
        l.setGoal("Improve pronunciation");
        l.updateProgress(10);
        System.out.println(l);
    }   
}
