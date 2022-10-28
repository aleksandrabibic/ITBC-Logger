package com.example.demo_itbc_logger.DataTransferObject;

import com.example.demo_itbc_logger.model.UserType;

public class UserRegistration {
    private String username;
    private String password;
    private String email;
    private UserType type;

    public UserRegistration() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserType getType() {
        return type;
    }

    public void setType(UserType type) {
        this.type = type;
    }
}

