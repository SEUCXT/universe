package com.seu.universe.entity;

import java.util.Objects;

public class MessageTranspondCnt {

    private long transpondId;
    private long messageId;
    private long transpondCnt;

    public long getTranspondId() {
        return transpondId;
    }

    public void setTranspondId(long transpondId) {
        this.transpondId = transpondId;
    }

    public long getMessageId() {
        return messageId;
    }

    public void setMessageId(long messageId) {
        this.messageId = messageId;
    }

    public long getTranspondCnt() {
        return transpondCnt;
    }

    public void setTranspondCnt(long transpondCnt) {
        this.transpondCnt = transpondCnt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MessageTranspondCnt that = (MessageTranspondCnt) o;
        return transpondId == that.transpondId &&
                messageId == that.messageId &&
                transpondCnt == that.transpondCnt;
    }

    @Override
    public int hashCode() {

        return Objects.hash(transpondId, messageId, transpondCnt);
    }

    @Override
    public String toString() {
        return "MessageTranspondCnt{" +
                "transpondId=" + transpondId +
                ", messageId=" + messageId +
                ", transpondCnt=" + transpondCnt +
                '}';
    }
}
