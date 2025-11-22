package com.aesp.pojo;

import java.util.Date;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.*;

@Entity
@Table(name = "Purchase")
public class Purchase {       //mua hàng
    //.1
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    @Column(name = "id")
    private long id; 
    @Column(name = "data")
    private Date data;
    @Column(name = "status")
    private String status;
    @Column(name = "amount")
    private double amount;
    //do quang hệ 1 0 
    @ManyToOne
    @JoinColumn(name = "learner_id")
    private Learner learner;
    //do quang hệ 1 0
    @ManyToOne
    @JoinColumn(name = "package_id")
    private Packages packages;
    @OneToOne
    @JoinColumn(name = "payment_id")
    private Payment payment;
}
