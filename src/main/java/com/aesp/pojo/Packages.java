package com.aesp.pojo;

import java.util.List;
import javax.persistence.*;

@Entity
@Table(name = "Packages")
public class Packages {
    //.1
    @Id
    @GeneratedValue
    @Column(name = "Id")
    public long id;
    @Column(name = "name")
    public String name;
    @Column(name = "prive")
    public double price;
    @Column(name = "duration")
    public int duration;
    @Column(name = "hasMentor")
    public boolean hasMentor;

    // quang hệ 1 0 vói purchase:.1
    public List<Purchase> purchase;
}
