package com.seu.universe.entity;

import java.util.Objects;

public class MessageLikeCnt {

    private long messageLikeId;
    private long messageId;
    private long likeCnt;

    public long getMessageLikeId() {
        return messageLikeId;
    }

    public void setMessageLikeId(long messageLikeId) {
        this.messageLikeId = messageLikeId;
    }

    public long getMessageId() {
        return messageId;
    }

    public void setMessageId(long messageId) {
        this.messageId = messageId;
    }

    public long getLikeCnt() {
        return likeCnt;
    }

    public void setLikeCnt(long likeCnt) {
        this.likeCnt = likeCnt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MessageLikeCnt that = (MessageLikeCnt) o;
        return messageLikeId == that.messageLikeId &&
                messageId == that.messageId &&
                likeCnt == that.likeCnt;
    }

    @Override
    public int hashCode() {

        return Objects.hash(messageLikeId, messageId, likeCnt);
    }

    @Override
    public String toString() {
        return "MessageLikeCnt{" +
                "messageLikeId=" + messageLikeId +
                ", messageId=" + messageId +
                ", likeCnt=" + likeCnt +
                '}';
    }
}
