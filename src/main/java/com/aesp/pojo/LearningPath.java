package com.aesp.pojo;

import jakarta.persistence.*;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "LearningPath")
public class LearningPath{//hành trình học
    //.1
    @Id
    @GeneratedValue
    @Column(name = "Id")
    private long id;
    @Column(name = "currentLevel")
    private String currentLevel;
    @Column(name = "progress")
    private double progress;
    @Column(name = "totalLession")
    private int totalLession;

    //vì quang hệ 1 Leamingpath và 1 với learner:.1
    @OneToOne
    @JoinColumn(name = "learner_id")
    @MapsId
    private Learner learner;
}