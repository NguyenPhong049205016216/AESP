package com.aesp.service;

public class PasswordUtil {

    public static String hashPassword(String rawPassword) { 
      
        if (rawPassword == null) return null;
        return new StringBuilder(rawPassword).reverse().toString();
    }
    
    // =======================================================
    // TRIỂN KHAI checkPassword (Nếu cũng bị Unimplemented)
    // =======================================================
    public static boolean checkPassword(String rawPassword, String hashedPassword) {
        if (rawPassword == null || hashedPassword == null) return false;
        return hashPassword(rawPassword).equals(hashedPassword);
    }

}
