package com.example.demo_itbc_logger.service;

import com.example.demo_itbc_logger.exception.UserLoginException;
import com.example.demo_itbc_logger.model.User;
import com.example.demo_itbc_logger.model.UserType;
import com.example.demo_itbc_logger.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.List;
@org.springframework.stereotype.Service
public class UserService {
    @Autowired
    protected UserRepository userRepository;

    public void save(User item) {
        if (item.getType() == null) item.setType(UserType.CLIENT);
        userRepository.save(item);
    }
    //logovanje korisnika
    public String login(String account, String password) {
        User dbUser = userRepository.findByUsername(account);
        if (dbUser == null) dbUser = userRepository.findByEmail(account);
        if (dbUser == null) throw new UserLoginException("Incorrect email or username");
        if (!dbUser.getPassword().equals(password)) throw new UserLoginException("Incorrect password");
        return dbUser.getUsername();
    }

    public void resetPassword(Long clientId, String newPassword) {
        User user = this.findById(clientId);
        user.setPassword(newPassword);
        this.save(user);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }


    public User findById(Long id) {
        return userRepository.findById(id).get();
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }


    public void update(User item) {
        userRepository.save(item);
    }
}


