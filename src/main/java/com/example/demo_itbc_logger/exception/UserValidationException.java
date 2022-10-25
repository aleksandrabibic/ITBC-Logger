package com.example.demo_itbc_logger.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class UserValidationException  extends ResponseStatusException {

    public UserValidationException(String reason) {
        super(HttpStatus.BAD_REQUEST, reason);
    }
}
