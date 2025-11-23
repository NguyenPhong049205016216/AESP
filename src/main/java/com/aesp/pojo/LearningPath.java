package com.aesp.pojo;

import jakarta.persistence.*;

@Entity
@Table(name = "Learning_path")
public class LearningPath{//hành trình học
    //.1
    @Id
    @GeneratedValue
    @Column(name = "learner_id")
    private long learnerId;
    @Column(name = "curren_level")
    private String currentLevel;
    @Column(name = "progress")
    private double progress;
    @Column(name = "total_lession")
    private int totalLession;

    //vì quang hệ 1 Leamingpath và 1 với learner:.1
    @OneToOne
    @JoinColumn(name = "learner_id")
    @MapsId
    private Learner learner;

    // Constructor mặc định (BẮT BUỘC)
    public LearningPath() {
    }
    public Long getLearnerId() { return learnerId; }
    public void setLearnerId(Long learnerId) { this.learnerId = learnerId; }

    public Learner getLearner() { return learner; }
    public void setLearner(Learner learner) { this.learner = learner; }
}