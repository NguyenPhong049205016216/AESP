package com.aesp.dao;

import com.aesp.pojo.Admin;
public class Admindao extends Userdao {
    //call lại contructor của lớp cha
    public Admindao(String persistenceName){
        super(persistenceName);
    }

    public boolean saveAdmin(Admin admin){
        return save(admin); 
    }

    public boolean updataAdmin(Admin admin){
        return update(admin);
    }

    public boolean deleteAdmin(long id){
        return delete(id);
    }
}
