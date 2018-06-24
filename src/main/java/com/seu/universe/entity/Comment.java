package com.seu.universe.entity;

import java.sql.Timestamp;
import java.util.Objects;

public class Comment {

    private long commentId;
    private String commentInfo;
    private Timestamp time;
    private int commentInfoStatus;
    private long userId;
    private long messageId;

    public long getCommentId() {
        return commentId;
    }

    public void setCommentId(long commentId) {
        this.commentId = commentId;
    }

    public String getCommentInfo() {
        return commentInfo;
    }

    public void setCommentInfo(String commentInfo) {
        this.commentInfo = commentInfo;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public int getCommentInfoStatus() {
        return commentInfoStatus;
    }

    public void setCommentInfoStatus(int commentInfoStatus) {
        this.commentInfoStatus = commentInfoStatus;
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
        Comment comment = (Comment) o;
        return commentId == comment.commentId &&
                commentInfoStatus == comment.commentInfoStatus &&
                userId == comment.userId &&
                messageId == comment.messageId &&
                Objects.equals(commentInfo, comment.commentInfo) &&
                Objects.equals(time, comment.time);
    }

    @Override
    public int hashCode() {

        return Objects.hash(commentId, commentInfo, time, commentInfoStatus, userId, messageId);
    }

    @Override
    public String toString() {
        return "Comment{" +
                "commentId=" + commentId +
                ", commentInfo='" + commentInfo + '\'' +
                ", time=" + time +
                ", commentInfoStatus=" + commentInfoStatus +
                ", userId=" + userId +
                ", messageId=" + messageId +
                '}';
    }
}
