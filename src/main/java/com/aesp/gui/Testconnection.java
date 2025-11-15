package com.aesp.gui;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;

@SpringBootApplication
@ComponentScan(basePackages = "com.aesp")
public class Testconnection{
    public static void main(String[] args) {
        SpringApplication.run(Testconnection.class, args);
    }
}