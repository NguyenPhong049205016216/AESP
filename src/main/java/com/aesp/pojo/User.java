package com.aesp.pojo;
import java.time.LocalDate;

import javax.persistence.*;
import javax.persistence.Table;
@Entity
@Table(name = "User")
public class User{                 //người dùng
    //truy cập sang các methor bên dưới:.1
    @Id
    @GeneratedValue
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
    private boolean status; //trạng thái
    @Column(name =  " CreatedAT")
    private LocalDate CreatedAT; //khởi tạo tại.
    //attribute này là "cập nhập tiến trình".1
    @Column(name = "UpdateAT")
    private LocalDate UpdatedAT; //cập nhập tại.

    //nguyên tắc đóng gói.1
    public User(long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.status = true;
        this.CreatedAT = LocalDate.now();
        //atribute này.1
        this.UpdatedAT = this.CreatedAT;
    }

    //getid 0 tham số:.1
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
    public String getImail() {
        return email;
    }
    public void setImail(String email) {
        this.email = email;
    }
    //getPassword 0 tham số:
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    
    public LocalDate getUpdatedAT() {
        return UpdatedAT;
    }

    public void setUpdatedAT(LocalDate updatedAT) {
        UpdatedAT = updatedAT;
    }

    //.1
    @Override
    public String toString() {
        return "User [id=" + id + ", name=" + name + ", email=" + email + "]";
    }






    
   
}

