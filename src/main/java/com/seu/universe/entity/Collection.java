package com.seu.universe.entity;

import java.util.Objects;

public class Collection {
    private long collectionId;
    private long time;
    private int status;
    private long userId;
    private long messageId;

    public long getCollectionId() {
        return collectionId;
    }

    public void setCollectionId(long collectionId) {
        this.collectionId = collectionId;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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
        Collection that = (Collection) o;
        return collectionId == that.collectionId &&
                time == that.time &&
                status == that.status &&
                userId == that.userId &&
                messageId == that.messageId;
    }

    @Override
    public int hashCode() {

        return Objects.hash(collectionId, time, status, userId, messageId);
    }

    @Override
    public String toString() {
        return "Collection{" +
                "collectionId=" + collectionId +
                ", time=" + time +
                ", status=" + status +
                ", userId=" + userId +
                ", messageId=" + messageId +
                '}';
    }
}
