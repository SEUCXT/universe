package com.seu.universe.task;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;

@Component
public class LikeTask implements Callable<Map<Long, Long>> {



    List<Long> messageidList;
    CountDownLatch latch;

    public LikeTask(List<Long> messageIdList, CountDownLatch latch) {
        this.messageidList = messageIdList;
        this.latch = latch;
    }

    @Override
    public Map<Long, Long> call() throws Exception {

        latch.countDown();
        return new Picture();
    }

}
