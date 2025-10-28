package com.aesp.pojo;

import javax.persistence.*;
import javax.persistence.Entity;

import org.hibernate.annotations.Comment;

@Entity
@Table(name = "Payment")
public class Payment {
    //.1
    @Id
    @GeneratedValue
    @Column(name = "Id")
    public long id;
    @Column(name = "methor")
    public String methor;
    @Column(name = "transactionCode")
    public String transactionCode;
    @Column(name = "amount")
    public double amount;
    // quang hệ 1 1 với Purchanse: .1
    public Purchase purchase;

}
