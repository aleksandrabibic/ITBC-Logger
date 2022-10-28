package com.example.demo_itbc_logger.DataTransferObject;

import com.example.demo_itbc_logger.model.LogType;

import java.sql.Date;

public class LogDto {
    private String message;
    private LogType logType;
    private Date date;

    public LogDto() {
    }

    public LogDto(String message, LogType logType, Date createdDate) {
        this.message = message;
        this.logType = logType;
        this.date = createdDate;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LogType getLogType() {
        return logType;
    }

    public void setLogType(LogType logType) {
        this.logType = logType;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
