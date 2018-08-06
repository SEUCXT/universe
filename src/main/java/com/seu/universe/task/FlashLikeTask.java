package com.seu.universe.task;

import com.seu.universe.config.Constants;
import com.seu.universe.entity.Like;
import com.seu.universe.service.LikeService;
import com.seu.universe.service.RedisService;
import com.seu.universe.utils.LikeRedisKeyUtil;
import com.seu.universe.utils.TimeUtil;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Map;
import java.util.Set;

@Component
public class FlashLikeTask extends QuartzJobBean {

    @Autowired
    private RedisService redisService;

    @Autowired
    private LikeService likeService;

    @Override
    public void executeInternal(JobExecutionContext context) throws JobExecutionException {
        Set<String> messageSet = redisService.smembersSetValue(Constants.MESSAGE_LIKE_SET);
        if (messageSet == null) {
            return;
        }
        long totalMessage = messageSet.size();
        for (int i = 0; i < totalMessage; i++) {
            long messageId = redisService.spopKey(Constants.MESSAGE_LIKE_SET);
            String messageLikeKey = LikeRedisKeyUtil.getMessageUserLikeSetKey(String.valueOf(messageId));
            Set<String> userIdSet = redisService.smembersSetValue(messageLikeKey);
            for (String str : userIdSet) {
                long userId = Long.parseLong(str);
                String messageLikeUserKey = LikeRedisKeyUtil.getMessageUserLikeKey(String.valueOf(messageId), String.valueOf(userId));
                Map<String, String> map = redisService.hgetAll(messageLikeUserKey);
                String timeStr = map.get("time");
                Timestamp time = TimeUtil.convertStringToTimeStamp(timeStr);
                likeService.addLike(messageId, userId, time);
                redisService.delKey(messageLikeUserKey);
            }
            String counterKey = LikeRedisKeyUtil.getMessageLikeCounterKey(String.valueOf(messageId));
            long cnt = Long.parseLong(redisService.get(counterKey));
            // todo 将cnt刷新到messageLikeCnt表中，再在redis中删除这个key
            redisService.delKey(counterKey);
            redisService.delKey(messageLikeKey);
        }
    }
}
