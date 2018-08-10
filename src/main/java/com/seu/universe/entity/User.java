package com.seu.universe.entity;

import java.sql.Timestamp;
import java.util.Objects;

public class User {
    private long userId;
    private String nickname;
    private String salt;
    private String password;
    private String email;
    private Timestamp registerTime;
    private int status;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
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

    public Timestamp getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Timestamp registerTime) {
        this.registerTime = registerTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", nickname='" + nickname + '\'' +
                ", salt='" + salt + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", registerTime=" + registerTime +
                ", status=" + status +
                '}';
    }
}
