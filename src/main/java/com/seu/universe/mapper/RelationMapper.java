package com.seu.universe.mapper;

import com.seu.universe.entity.Relation;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RelationMapper {

    void addRelation(Relation relation);

    List<Relation> getRelationByUserId(long userId);

    Relation getRelationBetweenUsers(long userId, long userById);
}
