package com.example.demo_itbc_logger.DataTransferObject;

import com.example.demo_itbc_logger.model.LogType;

public class NewLog {
    private String message;
    private int logType;

    public NewLog() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getLogType() {
        return logType;
    }

    public void setLogType(int logType) {
        this.logType = logType;
    }
}
