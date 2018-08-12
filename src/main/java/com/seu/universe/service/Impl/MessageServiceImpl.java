package com.seu.universe.service.Impl;

import com.alibaba.fastjson.JSON;
import com.seu.universe.config.Constants;
import com.seu.universe.config.ViewObject;
import com.seu.universe.entity.Message;
import com.seu.universe.entity.User;
import com.seu.universe.mapper.MessageMapper;
import com.seu.universe.mapper.UserMapper;
import com.seu.universe.service.MessageService;
import com.seu.universe.task.LikeTask;
import com.seu.universe.utils.TableUtil;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.FutureTask;
import java.util.concurrent.ThreadPoolExecutor;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private MessageMapper messageMapper;

    @Autowired
    private AmqpTemplate rabbitTemplate;

    @Autowired
    TaskExecutor taskExecutor;

    @Override
    public ViewObject publishMessage(String messageType, String messageInfo, String label, long pictureId, long userId) {
        ViewObject vo = new ViewObject();
        User user = userMapper.getUserByUserId(userId);
        String email = user.getEmail();
        StringBuilder tableName = new StringBuilder();
        String tmp = email.substring(0, email.lastIndexOf('@'));
        tableName.append("t_").append(tmp).append("_message");

        Message message = new Message();
        message.setMessageType(messageType);
        message.setMessageInfo(messageInfo);
        message.setCollectNum(0);
        message.setCommentNum(0);
        message.setTranspondNum(0);
        message.setLikeNum(0);
        message.setCommentNum(0);
        message.setLabel(label);
        Date date = new Date();
        Timestamp time = new Timestamp(date.getTime());
        message.setTime(time);
        message.setPictureId(pictureId);
        message.setUserId(userId);
        messageMapper.publishMessage(tableName.toString(), messageType, messageInfo, time, 0,
                0, 0, 0, 0, label, pictureId, userId, 0);
        this.rabbitTemplate.convertAndSend(Constants.PUBLIC_MESSAGE_TOPIC, JSON.toJSON(message).toString());
        vo.set(ViewObject.ERROR, 0).set(ViewObject.MESSAGE, "状态发布成功！");
        return vo;
    }

    @Override
    public ViewObject getMessageByMessageId(Long messageId, long userId) {
        ViewObject vo = new ViewObject();
        User user = userMapper.getUserByUserId(userId);
        String email = user.getEmail();
        String tableName = TableUtil.getTableName(email);
        Message message = messageMapper.getMessageByMessageId(tableName, messageId);
        vo.set(ViewObject.ERROR, 0).set(ViewObject.DATA, JSON.toJSON(message));
        return vo;
    }

    @Override
    public ViewObject getRelatedMessageWithPage(long userId, int pageNum, int pageSize) {
        ViewObject vo = new ViewObject();
        User user = userMapper.getUserByUserId(userId);
        String email = user.getEmail();
        String tableName = TableUtil.getTableName(email);
        int start = (pageNum+1) * pageSize;
        List<Message> message = messageMapper.getRelatedMessageWithPage(tableName, start, pageSize);
        List<Long> messgaIdList = new ArrayList<>();
        for (int i = 0; i < message.size(); i++) {
            messgaIdList.add(message.get(i).getMessageId());
        }
        try {
            CountDownLatch countDownLatch = new CountDownLatch(4);
            FutureTask<Map<Long, Long>> likeFutureTask = new FutureTask<>(new LikeTask(messgaIdList, countDownLatch));
            taskExecutor.execute(likeFutureTask);
            // todo 这边获取转发数、评论和评论数，占用两个线程, 一共是4个线程
            countDownLatch.await();
            Map<Long, Long> likeMap = likeFutureTask.get();
            for (int i = 0; i < message.size(); i++) {
                int cnt = likeMap.get(message.get(i).getMessageId()).intValue();
                message.get(i).setLikeNum(cnt);
            }
        } catch (Exception e) {

            e.printStackTrace();
        }
        vo.set(ViewObject.ERROR, 0).set(ViewObject.DATA, JSON.toJSON(message));
        return vo;
    }

    @Override
    public ViewObject getMessageByUserIdWithPage(long userId, int pageNum, int pageSize) {
        ViewObject vo = new ViewObject();
        User user = userMapper.getUserByUserId(userId);
        String email = user.getEmail();
        String tableName = TableUtil.getTableName(email);
        int start = (pageNum+1) * pageSize;
        List<Message> message = messageMapper.getMessageByUserIdWithPage(tableName, userId, start, pageSize);
        List<Long> messageIdList = new ArrayList<>();
        for (Message m : message) {
            messageIdList.add(m.getMessageId());
        }
        vo.set(ViewObject.ERROR, 0).set(ViewObject.DATA, JSON.toJSON(message));
        return vo;
    }

    @Override
    public  ViewObject deleteMessage(long userId, long messageId) {
        ViewObject vo = new ViewObject();
        User user = userMapper.getUserByUserId(userId);
        String email = user.getEmail();
        String tableName = TableUtil.getTableName(email);
        messageMapper.deleteMessage(tableName, messageId);
        Message message = messageMapper.getMessageByMessageId(tableName, messageId);
        rabbitTemplate.convertAndSend(Constants.DELETE_MESSAGE_TOPIC, JSON.toJSON(message).toString());
        vo.set(ViewObject.ERROR, 0).set(ViewObject.MESSAGE, "消息删除成功！");
        return vo;
    }

    @Override
    public ViewObject transpondMessage(long messageId, long userId) {
        ViewObject vo = new ViewObject();
        User user = userMapper.getUserByUserId(userId);
        String email = user.getEmail();
        StringBuilder tableName = new StringBuilder();
        String tmp = email.substring(0, email.lastIndexOf('@'));
        tableName.append("t_").append(tmp).append("_message");
        Message message = messageMapper.getMessageByMessageId(tableName.toString(), messageId);
        messageMapper.publishMessage(tableName.toString(), message.getMessageType(), message.getMessageInfo(), message.getTime(), 0,
                0, 0, 0, 0, message.getLabel(), message.getPictureId(), message.getUserId(), 2);
        vo.set(ViewObject.ERROR, 0).set(ViewObject.MESSAGE, "消息转发成功！");
        return vo;
    }

}
