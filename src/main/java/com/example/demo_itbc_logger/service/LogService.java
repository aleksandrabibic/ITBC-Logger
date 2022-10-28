package com.example.demo_itbc_logger.service;

import com.example.demo_itbc_logger.exception.UserNotFoundException;
import com.example.demo_itbc_logger.model.Log;
import com.example.demo_itbc_logger.model.LogType;
import com.example.demo_itbc_logger.model.User;
import com.example.demo_itbc_logger.repository.LogRepository;
import com.example.demo_itbc_logger.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;


import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@org.springframework.stereotype.Service
public class LogService {
    @Autowired
    private LogRepository logRepository;
    @Autowired
    private UserRepository userRepository;

    public void save(Log log, User user){
        User dbUser=userRepository.findByUsername(user.getUsername());
        if(dbUser==null) throw new UserNotFoundException(HttpStatus.UNAUTHORIZED,"Unauthorized user");
        log.setDate(Date.valueOf(LocalDate.now()));
        log.setUser(dbUser);
        logRepository.save(log);
    }

    public List<Log> search(Date from, Date to, String message, LogType type){
        return logRepository.search(from,to,message,type);
    }

}
