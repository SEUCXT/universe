package com.seu.universe.service.Impl;

import com.seu.universe.config.ViewObject;
import com.seu.universe.entity.Like;
import com.seu.universe.entity.MessageLikeCnt;
import com.seu.universe.mapper.LikeCntMapper;
import com.seu.universe.mapper.LikeMapper;
import com.seu.universe.service.LikeService;
import com.seu.universe.service.RedisService;
import com.seu.universe.utils.LikeRedisKeyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class LikeServiceImpl implements LikeService {

    @Autowired
    private LikeMapper likeMapper;

    @Autowired
    private RedisService redisService;

    @Autowired
    private LikeCntMapper likeCntMapper;

    @Override
    public ViewObject addLike(long messageId, long userId) {
        ViewObject vo = new ViewObject();
        Like like = new Like();
        Date date = new Date();
        Timestamp time = new Timestamp(date.getTime());
        like.setLikeTime(time);
        like.setMessageId(messageId);
        like.setUserId(userId);
        //int res = likeMapper.addLike(like);
        vo.set(ViewObject.ERROR, 0).set(ViewObject.MESSAGE, "点赞成功！");
        return vo;
    }

    @Override
    public ViewObject addLikeToRedis(long messageId, long messageUserId, long likeUserId) {
        ViewObject vo = new ViewObject();
        if (isLike(likeUserId, messageUserId)) {
            return vo.set(ViewObject.ERROR, 0).set(ViewObject.MESSAGE, "你已经点过赞，请不要重复操作！");
        }
        if (!redisService.sismemberSetValue(LikeRedisKeyUtil.MESSAGE_SET, String.valueOf(messageId))) {
            redisService.saddSetValue(LikeRedisKeyUtil.MESSAGE_SET, String.valueOf(messageId));
            redisService.set(LikeRedisKeyUtil.getMessageLikeCounterKey(String.valueOf(messageId)), String.valueOf(0));
        }
        String userLikeSetKey = LikeRedisKeyUtil.getMessageUserLikeSetKey(String.valueOf(messageId));
        // 如果没有赞过，就现在赞的set中添加userId
        redisService.saddSetValue(userLikeSetKey, String.valueOf(likeUserId));
        String userLikeKey = LikeRedisKeyUtil.getMessageUserLikeKey(String.valueOf(messageId), String.valueOf(likeUserId));
        Map<String, String> map = new HashMap<>();
        map.put("likeUserId", String.valueOf(likeUserId));
        Date date = new Date();
        Timestamp time = new Timestamp(date.getTime());
        map.put("time", String.valueOf(time));
        map.put("messageId", String.valueOf(messageId));
        map.put("messageUserId", String.valueOf(messageUserId));
        redisService.hmsetHash(userLikeKey, map);
        // 添加messaga_counter
        String messageCounterKey = LikeRedisKeyUtil.getMessageLikeCounterKey(String.valueOf(messageId));
        long lastCount = Long.parseLong(redisService.get(messageCounterKey));
        redisService.set(messageCounterKey, String.valueOf(lastCount + 1));
        return vo.set(ViewObject.ERROR, 0).set(ViewObject.MESSAGE, "点赞成功！");
    }

    public ViewObject cancelLike(long messageId, long userId) {
        ViewObject vo = new ViewObject();
        if (!isLike(userId, messageId)) {
            vo.set(ViewObject.ERROR, 1).set(ViewObject.MESSAGE, "你没有赞过该状态！");
        }
        if (redisService.sismemberSetValue(LikeRedisKeyUtil.MESSAGE_SET, String.valueOf(messageId))) {
            if (redisService.sismemberSetValue(LikeRedisKeyUtil.getMessageUserLikeSetKey(String.valueOf(messageId)), String.valueOf(userId))) {
                redisService.sremSetValue(LikeRedisKeyUtil.getMessageUserLikeSetKey(String.valueOf(messageId)), String.valueOf(userId));
                redisService.delKey(LikeRedisKeyUtil.getMessageUserLikeKey(String.valueOf(messageId), String.valueOf(userId)));
                // 添加messaga_counter
                String messageCounterKey = LikeRedisKeyUtil.getMessageLikeCounterKey(String.valueOf(messageId));

                long lastCount = Long.parseLong(redisService.get(messageCounterKey));
                redisService.set(messageCounterKey, String.valueOf(lastCount - 1));
                vo.set(ViewObject.ERROR, 0).set(ViewObject.MESSAGE, "取消赞成功！");
            }
        }
        Like like = likeMapper.getLike(messageId, userId);
        if (like == null) {
            vo.set(ViewObject.ERROR, 1).set(ViewObject.MESSAGE, "你没有赞过该状态！");
        } else {
            likeMapper.cancelLike(messageId, userId);
            vo.set(ViewObject.ERROR, 0).set(ViewObject.MESSAGE, "取消赞成功！");
        }
        return vo;
    }

    @Override
    public long getMessageLikeCnt(long messageId) {
        MessageLikeCnt messageLikeCnt = likeCntMapper.getMessageLikeCnt(messageId);
        if (redisService.sismemberSetValue(LikeRedisKeyUtil.MESSAGE_SET, String.valueOf(messageId))) {
            String redisKeyName = LikeRedisKeyUtil.getMessageLikeCounterKey(String.valueOf(messageId));
            Long likeCnt = messageLikeCnt.getLikeCnt() + Long.parseLong(redisService.get(redisKeyName));
            return likeCnt;
        } else {
            return messageLikeCnt.getLikeCnt();
        }
    }

    private boolean isLike(long userId, long messageId) {
        boolean likeInRedis = redisService.sismemberSetValue(LikeRedisKeyUtil.MESSAGE_SET, String.valueOf(messageId));
        if (likeInRedis) {
            String redisKeyName = LikeRedisKeyUtil.getMessageUserLikeSetKey(String.valueOf(messageId));
            boolean userIdRedis = redisService.sismemberSetValue(redisKeyName, String.valueOf(userId));
            if (userIdRedis) {
                return true;
            }
        }
        Like like = likeMapper.getLike(messageId, userId);
        if (like != null) {
            return true;
        }
        return false;
    }

}
