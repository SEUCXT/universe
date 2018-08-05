package com.seu.universe.controller;

import com.seu.universe.config.Constants;
import com.seu.universe.entity.Picture;
import com.seu.universe.service.LikeService;
import com.seu.universe.service.PictureService;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.concurrent.*;

@Controller
@RequestMapping("/test")
public class TestController {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    @Autowired
    private PictureService pictureService;

    @Autowired
    private LikeService likeService;

    @Autowired
    ThreadPoolTaskExecutor taskExecutor;


    @RequestMapping("/sendMq")
    @ResponseBody
    public void send() {
        String context = "hello " + new Date();
        System.out.println("Sender : " + context);
        this.rabbitTemplate.convertAndSend(Constants.TEST_TOPIC, context);
    }

    @RequestMapping("/thread")
    @ResponseBody
    public void testThread() throws Exception {
        CountDownLatch latch = new CountDownLatch(2);
        Future<Picture> result1 = taskExecutor.submit(new Task1(12, latch));
        Future<Long> result2 = taskExecutor.submit(new Task2(12, latch));
        latch.await();
        Picture picture = result1.get();
        long likeCnt = result2.get();
        System.out.println(picture);
        System.out.println(likeCnt);
    }

    class Task1 implements Callable<Picture> {
        long pictureId;
        CountDownLatch latch;

        public Task1(long pictureId, CountDownLatch latch) {
            this.pictureId = pictureId;
            this.latch = latch;
        }

        @Override
        public Picture call() throws Exception {
            //Picture picture = pictureService.getPictureById(pictureId);
            Thread.sleep(1000);
            latch.countDown();
            return new Picture();
        }
    }

    class Task2 implements Callable<Long> {

        long messageId;
        CountDownLatch latch;

        public Task2(long messageId, CountDownLatch latch) {
            this.messageId = messageId;

            this.latch = latch;
        }

        @Override
        public Long call() throws Exception {
            //long likeCnt = likeService.getMessageLikeCnt(messageId);
            Thread.sleep(2000);
            latch.countDown();
            return (long) 11;
        }
    }

}
