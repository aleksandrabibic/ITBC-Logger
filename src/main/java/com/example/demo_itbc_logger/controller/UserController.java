package com.example.demo_itbc_logger.controller;

import com.example.demo_itbc_logger.model.User;
import com.example.demo_itbc_logger.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/api/clients/register")
    public ResponseEntity<?> save(@RequestBody User user) {
        try {
            userService.save(user);
        } catch (DataIntegrityViolationException exception) {
            return ResponseEntity.status(409).body("Username/email must be unique.");
        }
        return ResponseEntity.status(201).build();
    }
}
