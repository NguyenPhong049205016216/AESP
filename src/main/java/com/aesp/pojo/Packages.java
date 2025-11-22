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
    private String name;
    @Column(name = "prive")
    private double prive;    
    @Column(name = "duration")
    private int duration;
    @Column(name = "hasMentor")
    private boolean hasMentor;
    // quang hệ 1 0 vói purchase:.1
    @OneToMany(mappedBy = "packages", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Purchase> purchase;
    public Packages(){}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public double getPrive() { return prive; }
    public void setPrive(double prive) { this.prive = prive; }

    public int getDuration() { return duration; }
    public void setDuration(int duration) { this.duration = duration; }

    public boolean isHasMentor() { return hasMentor; }
    public void setHasMentor(boolean hasMentor) { this.hasMentor = hasMentor; }

    public void setPurchase(List<Purchase> purchase) { this.purchase = purchase; }
}

