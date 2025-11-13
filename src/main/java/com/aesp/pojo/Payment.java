package com.aesp.pojo;

import jakarta.persistence.*;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import org.hibernate.annotations.Comment;

@Entity
@Table(name = "Payment")
public class Payment {     //thanh toán
    //.1
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "methor")
    public String methor;
    @Column(name = "transactionCode")
    public String transactionCode;
    @Column(name = "amount")
    public double amount;
    // quang hệ 1 1 với Purchanse: .1
    @OneToOne
    @JoinColumn(name = "purchase_id")
    private Purchase purchase;
    //

}
