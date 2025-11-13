package com.aesp.dao;

import com.aesp.pojo.Admin;
import org.springframework.stereotype.Repository;
public class Admindao extends Userdao {

    public boolean saveAdmin(Admin admin){
        return super.save(admin); 
    }

    public boolean updataAdmin(Admin admin){
        return super.update(admin);
    }

    public boolean deleteAdmin(long id){
        return super.delete(id);
    }
}
