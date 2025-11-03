package com.aesp.pojo;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorValue("ADMIN")
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