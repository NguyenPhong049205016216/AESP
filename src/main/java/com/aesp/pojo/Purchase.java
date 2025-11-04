package com.aesp.pojo;

import java.util.Date;
import javax.persistence.GeneratedValue;
import javax.persistence.*;

@Entity
@Table(name = "Purchase")
public class Purchase {       //mua hàng
    //.1
    @Id
    @GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
    @Column(name = "Id")
    public long id; 
    @Column(name = "data")
    public Date data;
    @Column(name = "status")
    public String status;
    @Column(name = "amount")
    public double amount;
    //do quang hệ 1 0 
    @ManyToOne
    @JoinColumn(name = "learner_id")
    public Learner learner;
    //do quang hệ 1 0
    @ManyToOne
    @JoinColumn(name = "package_id")
    public Packages packages;
}
