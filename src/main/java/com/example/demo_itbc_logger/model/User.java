package com.example.demo_itbc_logger.model;

import com.example.demo_itbc_logger.exception.UserValidationException;

import javax.persistence.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String username;
    private String password;
    @Column(unique = true)
    private String email;
    @Column
    private UserType type;

    public User() {
    }

    public User(String username) {
        this.username = username;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setPassword(String password) {
        if (password.length() < 8 & password.matches(".*[a-zA-Z].*") & password.matches(".*[0-9].*"))
            throw new UserValidationException("Password must at least 8 characters and one letter and one number");
        this.password = password;
    }

    public void setEmail(String email) {
        Pattern pattern = Pattern.compile("^(.+)@(.+)$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        if (!matcher.matches())
            throw new UserValidationException("Email must be valid");
        this.email = email;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserType getType() {
        return type;
    }

    public void setType(UserType type) {
        this.type = type;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }
}

