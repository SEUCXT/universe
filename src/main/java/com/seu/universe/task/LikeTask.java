package com.seu.universe.task;



import com.seu.universe.entity.MessageLikeCnt;
import com.seu.universe.service.LikeService;
import com.seu.universe.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;

@Component
public class LikeTask implements Callable<Map<Long, Long>> {

    List<Long> messageidList;
    CountDownLatch latch;


    @Autowired
    LikeService likeService;

    public LikeTask(List<Long> messageIdList, CountDownLatch latch) {
        this.messageidList = messageIdList;
        this.latch = latch;
    }

    @Override
    public Map<Long, Long> call() throws Exception {

        Map<Long, Long> resMap = new HashMap<>();
        for (long messageId : messageidList) {
            long messageLikeCnt = likeService.getMessageLikeCnt(messageId);
            resMap.put(messageId, messageLikeCnt);
        }
        latch.countDown();
        return resMap;
    }

}
