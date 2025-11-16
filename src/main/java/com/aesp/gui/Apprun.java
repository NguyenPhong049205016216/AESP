package com.aesp.gui;

import com.aesp.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
// =========================================================================================
// LỚP KHỞI ĐỘNG CHÍNH CỦA SPRING BOOT TEST (THAY THẾ main() CŨ)
// =========================================================================================
@SpringBootApplication 
@ComponentScan(basePackages = "com.aesp") 
@EnableJpaRepositories(basePackages = "com.aesp.repository")
@EntityScan(basePackages = "com.aesp.pojo") 
public class Apprun { 

    @Autowired
    private UserService userService; 
    public static void main(String[] args) {
        // KHỞI ĐỘNG SPRING BOOT CONTEXT (Để Spring quản lý các @Service, @Repository)
        SpringApplication.run(Apprun.class, args);
    }
    //http://localhost:8080/aesp/index.html
    //http://localhost:8080/aesp/
}