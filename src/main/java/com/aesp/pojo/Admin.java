package com.aesp.pojo;

import jakarta.persistence.*;

@Entity
@Table(name = "Admin")
//liên kết khóa chính với bảng cha User
@PrimaryKeyJoinColumn(name = "id")
public class Admin extends User{   //quản trị viên
    //.1
    public Admin(){
        super();
    }
    public Admin(long id, String name, String email){
        super(id, name, email);

    }
    public void viewReport(){
        return;
    }
    public void manageAcount(){
        return;
    }
    public void manageMentor(){
        return;
    }
    public void managePackages(){
        return;
    }

}