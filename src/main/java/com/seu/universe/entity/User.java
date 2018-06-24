package com.seu.universe.entity;

import java.sql.Timestamp;
import java.util.Objects;

public class User {
    private long userId;
    private String nickname;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User t_user = (User) o;
        return userId == t_user.userId &&
                status == t_user.status &&
                Objects.equals(nickname, t_user.nickname) &&
                Objects.equals(password, t_user.password) &&
                Objects.equals(email, t_user.email) &&
                Objects.equals(registerTime, t_user.registerTime);
    }

    @Override
    public int hashCode() {

        return Objects.hash(userId, nickname, password, email, registerTime, status);
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", nickname='" + nickname + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", registerTime=" + registerTime +
                ", status=" + status +
                '}';
    }
}
