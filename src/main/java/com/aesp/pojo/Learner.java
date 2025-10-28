package com.aesp.pojo;

import java.util.List;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;
//.1
@Entity
@Table(name = "Learner")
public class Learner extends User {
    @Column(name ="level")
    private String level; //cấp độ
    @Column(name = "goal")
    private String goal;  //mục tiêu 
    @Column(name = "progress")
    private double progress; //tiến triển
    //.1
    public Learner(long id, String name, String email,String level){
        super(id, name, email);
        this.level = level;
        this.progress = 0.0;
    }
    //Class kế thừa lớp.1
    public String getLevel() {
        return level;
    }
    public String getGoal() {
        return goal;
    }
    public double getProgress() {
        return progress;
    }
    public void setGoal(String goal) {
        this.goal = goal;
    }

    //.2
    public void updateProgress(double delta){
        this.progress += delta;
        if(this.progress < 0 ) this.progress = 0;
        if(this.progress >0) this.progress = 100;
    }
    //.1
    public void takeAssessment(){
    return;
    }
    public void startSession(){
    return; 
    }
    public void viewProgress(){
    return;
    }
    public void upgradePackage(){
    return;
    }   
    @Override
    public String toString() {
        return "Learner [level=" + level + ", goal=" + goal + ", progress=" + progress + ", getId()=" + getId()
                + ", getName()=" + getName() + "]";
    }
    //Khai báo đối tượng lồng nhau:.1
    //do đường liên kết use case: nên một Learner có nhiều Assessment:.1
    public List<Assessment> assessment;

    public List<ConversationSession> conversationSession;
    
    public List<Purchase> purchase; 
    

}