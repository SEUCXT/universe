package com.seu.universe.entity;

import java.sql.Timestamp;
import java.util.Objects;

public class Picture {
    private long pictureId;
    private String pictureUrl;
    private Timestamp time;
    private long userId;

    public long getPictureId() {
        return pictureId;
    }

    public void setPictureId(long pictureId) {
        this.pictureId = pictureId;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Picture picture = (Picture) o;
        return pictureId == picture.pictureId &&
                userId == picture.userId &&
                Objects.equals(pictureUrl, picture.pictureUrl) &&
                Objects.equals(time, picture.time);
    }

    @Override
    public int hashCode() {

        return Objects.hash(pictureId, pictureUrl, time, userId);
    }

    @Override
    public String toString() {
        return "Picture{" +
                "pictureId=" + pictureId +
                ", pictureUrl='" + pictureUrl + '\'' +
                ", time=" + time +
                ", userId=" + userId +
                '}';
    }
}
