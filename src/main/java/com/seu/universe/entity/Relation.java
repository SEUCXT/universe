package com.seu.universe.entity;

import java.sql.Timestamp;
import java.util.Objects;

public class Relation {

    private long relationId;
    private Timestamp time;
    private String relationType;
    private String relationGroup;
    private long userId;
    private long userById;

    public long getRelationId() {
        return relationId;
    }

    public void setRelationId(long relationId) {
        this.relationId = relationId;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public String getRelationType() {
        return relationType;
    }

    public void setRelationType(String relationType) {
        this.relationType = relationType;
    }

    public String getRelationGroup() {
        return relationGroup;
    }

    public void setRelationGroup(String relationGroup) {
        this.relationGroup = relationGroup;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getUserById() {
        return userById;
    }

    public void setUserById(long userById) {
        this.userById = userById;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Relation relation = (Relation) o;
        return relationId == relation.relationId &&
                userId == relation.userId &&
                userById == relation.userById &&
                Objects.equals(time, relation.time) &&
                Objects.equals(relationType, relation.relationType) &&
                Objects.equals(relationGroup, relation.relationGroup);
    }

    @Override
    public int hashCode() {

        return Objects.hash(relationId, time, relationType, relationGroup, userId, userById);
    }

    @Override
    public String toString() {
        return "Relation{" +
                "relationId=" + relationId +
                ", time=" + time +
                ", relationType='" + relationType + '\'' +
                ", relationGroup='" + relationGroup + '\'' +
                ", userId=" + userId +
                ", userById=" + userById +
                '}';
    }
}
