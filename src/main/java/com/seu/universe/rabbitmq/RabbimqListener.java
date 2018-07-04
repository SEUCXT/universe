package com.seu.universe.rabbitmq;

import com.alibaba.fastjson.JSON;
import com.rabbitmq.client.Channel;
import com.seu.universe.config.Constants;
import com.seu.universe.entity.Like;
import com.seu.universe.entity.Message;
import com.seu.universe.entity.Relation;
import com.seu.universe.entity.User;
import com.seu.universe.mapper.LikeMapper;
import com.seu.universe.mapper.MessageMapper;
import com.seu.universe.mapper.RelationMapper;
import com.seu.universe.mapper.UserMapper;
import com.seu.universe.utils.MailUtil;
import com.seu.universe.utils.TableUtil;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RabbimqListener {

    @Autowired
    private MailUtil mailUtil;

    @Autowired
    private RelationMapper relationMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private MessageMapper messageMapper;

    @Autowired
    private LikeMapper likeMapper;

    @RabbitHandler
    @RabbitListener(queues = Constants.TEST_TOPIC)
    public void testAck(String email, org.springframework.amqp.core.Message message, Channel channel) throws Exception{
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }

    @RabbitHandler
    @RabbitListener(queues = Constants.EMAIL_TOPIC)
    public void registerEmail(String email) {
        String deliver = Constants.SYSTEM_EMAIL;
        String[] receiver = {email};
        String[] carbonCopy = {};
        String subject = "Universe注册成功";
        StringBuilder content = new StringBuilder();
        content.append("恭喜你，邮箱为").append(email).append("的账号注册Universe成功！");
        try {
            mailUtil.sendSimpleEmail(deliver, receiver, carbonCopy, subject, content.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RabbitHandler
    @RabbitListener(queues = Constants.PUBLIC_MESSAGE_TOPIC)
    public void pushMessage(String info) {
        Message message = JSON.parseObject(info, Message.class);
        List<Relation> relationList = relationMapper.getRelationByUserId(message.getUserId());
        for (Relation relation : relationList) {
            User user = userMapper.getUserByUserId(relation.getUserById());
            String email = user.getEmail();
            StringBuilder tableName = new StringBuilder();
            String tmp = email.substring(0, email.lastIndexOf('@'));
            tableName.append("t_").append(tmp).append("_message");
            messageMapper.publishMessage(tableName.toString(), message.getMessageType(), message.getMessageInfo(), message.getTime(), 0,
                    0, 0, 0, 0, message.getLabel(), message.getPictureId(), message.getUserId());
        }
    }

    @RabbitHandler
    @RabbitListener(queues = Constants.DELETE_MESSAGE_TOPIC)
    public void deleteMessage(String info) {
        Message message = JSON.parseObject(info, Message.class);
        List<Relation> relationList = relationMapper.getRelationByUserId(message.getUserId());
        for (Relation relation : relationList) {
            User user = userMapper.getUserByUserId(relation.getUserById());
            String email = user.getEmail();
            String tableName = TableUtil.getTableName(email);
            messageMapper.deleteMessage(tableName,message.getMessageId());
        }
    }

    @RabbitHandler
    @RabbitListener(queues = Constants.LIKE_TOPIC)
    public void pushLikeMessage(String info) {
        Like like = JSON.parseObject(info, Like.class);

        //Message message = messageMapper.getMessageByMessageId(like.getMessageId());
    }
}
