package com.aesp.pojo;

import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name = "Purchase")
public class Purchase {
    //.1
    @Id
    @GeneratedValue
    @Column(name = "Id")
    public long id; 
    @Column(name = "data")
    public Date data;
    @Column(name = "status")
    public String status;
    @Column(name = "amount")
    public double amount;
    //do quang hệ 1 0 
    public Learner learner;
    //do quang hệ 1 0
    public Packages packages;
}
