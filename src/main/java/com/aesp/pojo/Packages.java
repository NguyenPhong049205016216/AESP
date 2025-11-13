package com.aesp.pojo;

import java.util.List;
import jakarta.persistence.CascadeType;
import jakarta.persistence.*;

@Entity
@Table(name = "Packages")
public class Packages {      //gói học
    //.1
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "name")
    public String name;
    @Column(name = "prive")
    public double price;
    @Column(name = "duration")
    public int duration;
    @Column(name = "hasMentor")
    public boolean hasMentor;

    // quang hệ 1 0 vói purchase:.1
    @OneToMany(mappedBy = "packages", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Purchase> purchase;
}
