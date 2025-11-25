package com.aesp.pojo;
import java.time.LocalDate;

import jakarta.persistence.*;
@Entity
@Table(name = "[User]")
//#Chiến lược kế thừa: bảng con nối JOINED
@Inheritance(strategy = InheritanceType.JOINED)
//#chiến lược kế thừa: bảng đơn
//@Inheritance(strategy = InheritanceType.SINGLE_TABLE)//chiến lược kế thừa: bảng đơn
//@DiscriminatorColumn(name = "User_Type", discriminatorType = DiscriminatorType.STRING)
public class User{//người dùng
    //truy cập sang các methor bên dưới:.1
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "name")
    private String name; 
    @Column(name = "email")
    private String email;
    //Has sau này:.1
    @Column(name = "password")
    private String password; 
    @Column(name = "role")
    private String role; 
    @Column(name = "status")
    private String status; 
    @Column(name =  "CreatedAT")
    private LocalDate createdAT; 
    @Column(name = "UpdateAT")
    private LocalDate updatedAT; 
    public User(){  
    }
    //nguyên tắc đóng gói.1
    public User(long id, String name, String email){
        this.id = id;
        this.name = name;
        this.email = email;
        this.status = status;
        this.createdAT = LocalDate.now();
        this.updatedAT = this.createdAT;
    }
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    //getName 0 tham số:.
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    //getImail 0 tham số:.
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    //getPassword 0 tham số:
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    public void setCreatedAt(LocalDate createdAT ){
        this.createdAT = createdAT;
    }
    public void setUpdatedAT(LocalDate updatedAT) {
        this.updatedAT = updatedAT;
    }
    @Override
    public String toString() {
        return "User [id=" + id + ", name=" + name + ", email=" + email + "]";
    }
}

