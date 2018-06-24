package com.seu.universe.service;

import com.seu.universe.config.ViewObject;

public interface RelationService {

    ViewObject addRelation(String relationType, String groupname, long userId, long userById);

    ViewObject getRelationByUserId(long userId);

    ViewObject getRelationBetweenUsers(long userId, long userById);

}
