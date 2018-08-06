package com.seu.universe.service;


import com.seu.universe.config.ViewObject;

import java.sql.Timestamp;

public interface LikeService {

    /**
     * 添加点赞（直接写数据库）
     *
     * @param messageId
     * @param userId
     * @return
     */
    ViewObject addLike(long messageId, long userId, Timestamp timestamp);

    /**
     * 获取状态的点赞数
     *
     * @param messageId
     * @return
     */
    long getMessageLikeCnt(long messageId);

    /**
     * 通过redis的方式点赞
     *
     * @param messageId
     * @param messageUserId
     * @param likeUserId
     * @return
     */
    ViewObject addLikeToRedis(long messageId, long messageUserId, long likeUserId);
}
