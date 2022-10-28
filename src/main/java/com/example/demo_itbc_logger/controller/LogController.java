package com.example.demo_itbc_logger.controller;

import com.example.demo_itbc_logger.DataTransferObject.LogDto;
import com.example.demo_itbc_logger.DataTransferObject.NewLog;
import com.example.demo_itbc_logger.model.Log;
import com.example.demo_itbc_logger.model.LogType;
import com.example.demo_itbc_logger.model.User;
import com.example.demo_itbc_logger.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Date;
import java.util.List;
@RestController
public class LogController {
    @Autowired
    private LogService service;

    @PostMapping("/api/logs/create")
    public ResponseEntity<?> save(@RequestHeader(name = HttpHeaders.AUTHORIZATION) String username,
                                  @RequestBody NewLog dto) {
        try {
            Log log = new Log(dto.getMessage(), LogType.values()[dto.getLogType()]);
            service.save(log, new User(username));
        } catch (IndexOutOfBoundsException ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Incorrect log type");
        }
        return ResponseEntity.ok().build();
    }

    @GetMapping("/api/logs/search")
    public ResponseEntity<?> find(@RequestHeader(name = HttpHeaders.AUTHORIZATION) String username,
                                  @RequestParam(value = "dateFrom", defaultValue = "") String dateFrom,
                                  @RequestParam(value = "dateTo", defaultValue = "") String dateTo,
                                  @RequestParam(value = "message", defaultValue = "") String message,
                                  @RequestParam(value = "logType", defaultValue = "") LogType logType) {
        Date from = null;
        Date to = null;
        if (!dateFrom.isBlank()) from = Date.valueOf(dateFrom);
        if (!dateTo.isBlank()) to = Date.valueOf(dateTo);
        List<Log> logList = service.search(from, to, message, logType);
        List<LogDto> logDtoList = logList.stream().map(log -> new LogDto(log.getMessage(), log.getType(), (Date) log.getDate())).toList();
        return ResponseEntity.ok(logDtoList);
    }
}
