package com.seu.universe.service.Impl;

import com.alibaba.fastjson.JSON;
import com.seu.universe.config.Constants;
import com.seu.universe.config.ViewObject;
import com.seu.universe.entity.Message;
import com.seu.universe.entity.User;
import com.seu.universe.mapper.MessageMapper;
import com.seu.universe.mapper.UserMapper;
import com.seu.universe.service.MessageService;
import com.seu.universe.utils.TableUtil;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.View;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private MessageMapper messageMapper;

    @Autowired
    private AmqpTemplate rabbitTemplate;

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
