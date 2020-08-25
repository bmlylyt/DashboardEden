package com.example.UserCeneter.RPCDomain.req;

import java.io.Serializable;

/**
 * This class is used to map the inputs from registration table
 */
public class RegisterRequest implements Serializable {

    private static final long serialVersionUID = -653705860469900292L;

    private String username;
    private String password;
    private String email;
    private String captcha;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }
}
