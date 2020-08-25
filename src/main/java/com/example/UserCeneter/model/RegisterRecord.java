package com.example.UserCeneter.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="register_record")
public class RegisterRecord implements Serializable {


    private static final long serialVersionUID = 1598958280617915678L;

    @Id
    @Column
    private String id;
    @Column
    private String username;
    @Column
    private String email;
    @Column
    private String captcha;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public void setCaptcha(String capcha) {
        this.captcha = capcha;
    }

    public Date getSentTime() {
        return sentTime;
    }

    public void setSentTime(Date sentTime) {
        this.sentTime = sentTime;
    }

    private Date sentTime;


}
