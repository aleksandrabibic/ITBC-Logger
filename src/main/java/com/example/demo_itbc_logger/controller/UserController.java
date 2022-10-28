package com.example.demo_itbc_logger.controller;

import com.example.demo_itbc_logger.DataTransferObject.Login;
import com.example.demo_itbc_logger.DataTransferObject.NewPassword;
import com.example.demo_itbc_logger.DataTransferObject.Token;
import com.example.demo_itbc_logger.DataTransferObject.UserRegistration;
import com.example.demo_itbc_logger.exception.UserLoginException;
import com.example.demo_itbc_logger.exception.UserValidationException;
import com.example.demo_itbc_logger.model.User;
import com.example.demo_itbc_logger.model.UserType;
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
    public ResponseEntity<?> save(@RequestBody UserRegistration dto) {
        try {
            User user = new User();
            user.setType(dto.getType());
            user.setUsername(dto.getUsername());
            user.setEmail(dto.getEmail());
            user.setPassword(dto.getPassword());
            userService.save(user);
        } catch (UserValidationException ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
        } catch (DataIntegrityViolationException exception) {
            return ResponseEntity.status(409).body("Username/email must be unique.");
        }
        return ResponseEntity.status(201).build();
    }

    @PostMapping("/api/clients/login")
    public ResponseEntity<?> login(@RequestBody Login dto) {
        try {
            String username = userService.login(dto.getAccount(), dto.getPassword());
            return ResponseEntity.ok(new Token(username));
        } catch (UserLoginException ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
        }

    }

    @GetMapping("/api/clients")
    public ResponseEntity<?> findAll(@RequestHeader(name = HttpHeaders.AUTHORIZATION) String username) {
        authorizeAdmin(username);
        return ResponseEntity.ok(userService.findAll());
    }

    @PatchMapping("/api/clients/{clientId}/reset-password")
    public ResponseEntity<?> resetPassword(@RequestHeader(name = HttpHeaders.AUTHORIZATION) String username,
                                           @PathVariable Long clientId,
                                           @RequestBody NewPassword newPasswordDto) {
        authorizeAdmin(username);
        userService.resetPassword(clientId, newPasswordDto.getPassword());
        return ResponseEntity.status(204).build();
    }

    private void authorizeAdmin(String username) {
        User reqUser = userService.findByUsername(username);
        if (reqUser == null) throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Incorrect token");
        if (!reqUser.getType().equals(UserType.ADMIN))
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Correct token, but not admin");
    }
}
