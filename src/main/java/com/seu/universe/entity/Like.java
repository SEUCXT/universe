package com.seu.universe.entity;

import java.sql.Timestamp;
import java.util.Objects;

public class Like {
    private long likeId;
    private Timestamp likeTime;
    private long userId;
    private long messageId;

    public long getLikeId() {
        return likeId;
    }

    public void setLikeId(long likeId) {
        this.likeId = likeId;
    }

    public Timestamp getLikeTime() {
        return likeTime;
    }

    public void setLikeTime(Timestamp likeTime) {
        this.likeTime = likeTime;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getMessageId() {
        return messageId;
    }

    public void setMessageId(long messageId) {
        this.messageId = messageId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Like like = (Like) o;
        return likeId == like.likeId &&
                userId == like.userId &&
                messageId == like.messageId &&
                Objects.equals(likeTime, like.likeTime);
    }

    @Override
    public int hashCode() {

        return Objects.hash(likeId, likeTime, userId, messageId);
    }

    @Override
    public String toString() {
        return "Like{" +
                "likeId=" + likeId +
                ", likeTime=" + likeTime +
                ", userId=" + userId +
                ", messageId=" + messageId +
                '}';
    }
}
