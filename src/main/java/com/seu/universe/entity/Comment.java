package com.seu.universe.entity;

import java.sql.Timestamp;
import java.util.Objects;

public class Comment {

    private long commentId;
    private String content;
    private Timestamp time;
    private int status;
    private long fromUserId;
    private long messageId;
    private long toUserId;

    public long getCommentId() {
        return commentId;
    }

    public void setCommentId(long commentId) {
        this.commentId = commentId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public long getFromUserId() {
        return fromUserId;
    }

    public void setFromUserId(long fromUserId) {
        this.fromUserId = fromUserId;
    }

    public long getMessageId() {
        return messageId;
    }

    public void setMessageId(long messageId) {
        this.messageId = messageId;
    }

    public long getToUserId() {
        return toUserId;
    }

    public void setToUserId(long toUserId) {
        this.toUserId = toUserId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment = (Comment) o;
        return commentId == comment.commentId &&
                status == comment.status &&
                fromUserId == comment.fromUserId &&
                messageId == comment.messageId &&
                toUserId == comment.toUserId &&
                Objects.equals(content, comment.content) &&
                Objects.equals(time, comment.time);
    }

    @Override
    public int hashCode() {

        return Objects.hash(commentId, content, time, status, fromUserId, messageId, toUserId);
    }

    @Override
    public String toString() {
        return "Comment{" +
                "commentId=" + commentId +
                ", content='" + content + '\'' +
                ", time=" + time +
                ", status=" + status +
                ", fromUserId=" + fromUserId +
                ", messageId=" + messageId +
                ", toUserId=" + toUserId +
                '}';
    }
}
