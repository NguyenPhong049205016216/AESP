package com.aesp.pojo;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "LearningPath")
public class LearningPath{//hành trình học
    //.1
    @Id
    @GeneratedValue
    @Column(name = "Id")
    private long id;
    @Column(name = "currentLevel")
    public String currentLevel;
    @Column(name = "progress")
    public double progress;
    @Column(name = "totalLession")
    public int totalLession;

    //vì quang hệ 1 Leamingpath và 1 với learner:.1
    @OneToOne
    @JoinColumn(name = "learner_id")
    @MapsId
    private Learner learner;
}