package com.seu.universe.service.Impl;

import com.alibaba.fastjson.JSON;
import com.seu.universe.config.ViewObject;
import com.seu.universe.entity.Relation;
import com.seu.universe.mapper.RelationMapper;
import com.seu.universe.service.RelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Service
public class RelationServiceImpl implements RelationService {

    @Autowired
    private RelationMapper relationMapper;

    @Override
    public ViewObject addRelation(String relationType, String groupname, long userId, long userById) {
        ViewObject vo = new ViewObject();
        Relation relation = new Relation();
        relation.setRelationType(relationType);
        relation.setRelationGroup(groupname);
        relation.setUserId(userId);
        relation.setUserById(userById);
        Date date = new Date();
        Timestamp time = new Timestamp(date.getTime());
        relation.setTime(time);
        relationMapper.addRelation(relation);
        vo.set(ViewObject.ERROR, 0).set(ViewObject.MESSAGE, "添加好友成功！");
        return vo;
    }

    @Override
    public ViewObject getRelationByUserId(long userId) {
        ViewObject vo = new ViewObject();
        List<Relation> relationList = relationMapper.getRelationByUserId(userId);
        Object jsonStr = JSON.toJSON(relationList);
        vo.set(ViewObject.ERROR, 0).set(ViewObject.DATA, jsonStr);
        return vo;
    }

    @Override
    public ViewObject getRelationBetweenUsers(long userId, long userById) {
        ViewObject vo = new ViewObject();
        Relation relation = relationMapper.getRelationBetweenUsers(userId, userById);
        if (relation == null) {
            vo.set(ViewObject.ERROR, 1).set(ViewObject.MESSAGE, "该用户之间尚无建立关系！");
            return vo;
        }
        vo.set(ViewObject.ERROR, 0).set(ViewObject.DATA, JSON.toJSON(relation));
        return vo;
    }

}
