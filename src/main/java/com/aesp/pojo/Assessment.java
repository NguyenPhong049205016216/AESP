package com.aesp.pojo;

public class Assessment {
    //Một leaner sẻ có nhiều Assessment:.1
    public long id;
    public double score;
    public String feedback;
    public String level;
    //Mộ Assessment thuộc về 1 Learner:.1
    public Learner learner;
    public Mentor mentor;

    public double getScore() {
        return score;
    }
    
    public void setScore(double score) {
        this.score = score;
    }
    

    

}
