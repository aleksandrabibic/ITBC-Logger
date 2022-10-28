package com.example.demo_itbc_logger.DataTransferObject;

public class Login {
    //"account": "string", // email or username
    //    "password": "string"
    private String account;
    private String password;

    public Login() {
    }

    public Login(String account, String password) {
        this.account = account;
        this.password = password;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
