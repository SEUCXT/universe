package com.seu.universe.entity;

import java.sql.Timestamp;
import java.util.Objects;

public class Message {
    private long messageId;
    private String messageType;
    private String messageInfo;
    private Timestamp time;
    private int collectNum;
    private int commentNum;
    private int readNum;
    private int likeNum;
    private int transpondNum;
    private String label;
    private long pictureId;
    private long userId;
    private int status; // 0 自己发的状态；1 朋友发的状态； 2 转发的状态

    public long getMessageId() {
        return messageId;
    }

    public void setMessageId(long messageId) {
        this.messageId = messageId;
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public String getMessageInfo() {
        return messageInfo;
    }

    public void setMessageInfo(String messageInfo) {
        this.messageInfo = messageInfo;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public int getCollectNum() {
        return collectNum;
    }

    public void setCollectNum(int collectNum) {
        this.collectNum = collectNum;
    }

    public int getCommentNum() {
        return commentNum;
    }

    public void setCommentNum(int commentNum) {
        this.commentNum = commentNum;
    }

    public int getReadNum() {
        return readNum;
    }

    public void setReadNum(int readNum) {
        this.readNum = readNum;
    }

    public int getLikeNum() {
        return likeNum;
    }

    public void setLikeNum(int likeNum) {
        this.likeNum = likeNum;
    }

    public int getTranspondNum() {
        return transpondNum;
    }

    public void setTranspondNum(int transpondNum) {
        this.transpondNum = transpondNum;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public long getPictureId() {
        return pictureId;
    }

    public void setPictureId(long pictureId) {
        this.pictureId = pictureId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
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
        Message message = (Message) o;
        return messageId == message.messageId &&
                collectNum == message.collectNum &&
                commentNum == message.commentNum &&
                readNum == message.readNum &&
                likeNum == message.likeNum &&
                transpondNum == message.transpondNum &&
                pictureId == message.pictureId &&
                userId == message.userId &&
                status == message.status &&
                Objects.equals(messageType, message.messageType) &&
                Objects.equals(messageInfo, message.messageInfo) &&
                Objects.equals(time, message.time) &&
                Objects.equals(label, message.label);
    }

    @Override
    public int hashCode() {

        return Objects.hash(messageId, messageType, messageInfo, time, collectNum, commentNum, readNum, likeNum, transpondNum, label, pictureId, userId, status);
    }

    @Override
    public String toString() {
        return "Message{" +
                "messageId=" + messageId +
                ", messageType='" + messageType + '\'' +
                ", messageInfo='" + messageInfo + '\'' +
                ", time=" + time +
                ", collectNum=" + collectNum +
                ", commentNum=" + commentNum +
                ", readNum=" + readNum +
                ", likeNum=" + likeNum +
                ", transpondNum=" + transpondNum +
                ", label='" + label + '\'' +
                ", pictureId=" + pictureId +
                ", userId=" + userId +
                ", status=" + status +
                '}';
    }
}