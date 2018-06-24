package com.seu.universe.service.Impl;

import com.seu.universe.config.Constants;
import com.seu.universe.config.ViewObject;
import com.seu.universe.entity.User;
import com.seu.universe.mapper.UserMapper;
import com.seu.universe.service.RedisService;
import com.seu.universe.service.UserService;
import com.seu.universe.utils.MailUtil;
import com.seu.universe.utils.VerificationUtil;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.sql.Timestamp;
import java.util.Date;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private MailUtil mailUtil;

    @Autowired
    private RedisService redisService;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private AmqpTemplate rabbitTemplate;

    @Override
    public ViewObject getVertificationCode(String email) {
        ViewObject vo = new ViewObject();
        User oldUser = new User();
        oldUser = userMapper.getUserByEmail(email);
        if (oldUser != null) {
            vo.set(ViewObject.ERROR, 1)
                    .set(ViewObject.MESSAGE, "该邮箱已经注册！");
            return vo;
        }
        String deliver = Constants.SYSTEM_EMAIL;
        String[] receiver = {email};
        String[] carbonCopy = {};
        String subject = "Universe注册验证";
        StringBuilder content = new StringBuilder();
        content.append("Universe注册验证码：");
        String vertificationCode = VerificationUtil.getRandNum(6);
        content.append(vertificationCode).append(" 。");
        content.append("验证码15分钟内有效！");

        try {
            mailUtil.sendSimpleEmail(deliver, receiver, carbonCopy, subject, content.toString());
            redisService.set(email, vertificationCode);
            vo.set(ViewObject.ERROR, 0).set(ViewObject.MESSAGE, "获取验证码成功！");
        } catch (Exception e) {
            e.printStackTrace();
            vo.set(ViewObject.ERROR, 1).set(ViewObject.MESSAGE, "获取验证码失败！");
        }
        return vo;
    }


    @Override
    @Transactional
    public ViewObject userRegister(String email, String nickname, String password) {
        ViewObject vo = new ViewObject();
        if (StringUtils.isEmpty(email)) {
            vo.set(ViewObject.ERROR, 1)
                    .set(ViewObject.MESSAGE, "email不能为空！");
        } else if (StringUtils.isEmpty(nickname)) {
            vo.set(ViewObject.ERROR, 2)
                    .set(ViewObject.MESSAGE, "昵称不能为空！");
        }
        User user = new User();
        user.setEmail(email);
        user.setNickname(nickname);
        user.setPassword(password);
        user.setStatus(0);
        Date date = new Date();
        Timestamp time = new Timestamp(date.getTime());
        user.setRegisterTime(time);
        userMapper.addUser(user);
        StringBuilder sb = new StringBuilder();
        String tmp = email.substring(0, email.lastIndexOf('@'));
        sb.append("t_").append(tmp).append("_message");
        userMapper.createUserMessageTable(sb.toString());
        vo.set(ViewObject.ERROR, 0).set(ViewObject.MESSAGE, "注册成功！");
        this.rabbitTemplate.convertAndSend(Constants.EMAIL_TOPIC, email);
        return vo;
    }

}
