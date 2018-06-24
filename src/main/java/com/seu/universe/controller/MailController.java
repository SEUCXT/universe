package com.seu.universe.controller;

import com.seu.universe.service.RedisService;
import com.seu.universe.utils.MailUtil;
import com.seu.universe.utils.VerificationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Timestamp;
import java.util.Date;

@Controller
@RequestMapping("/mail")
public class MailController {

    @Autowired
    private MailUtil mailUtil;

    @Autowired
    private RedisService redisService;

    @RequestMapping("/redis")
    @ResponseBody
    public void redisTest() {
        redisService.set("ssss", "ajsdkaskd");
    }

    @RequestMapping("/send")
    @ResponseBody
    public void sendEmail() {
        String deliver = "chenxt0910@163.com";
        String[] receiver = {"1785832339@qq.com"};
        String[] carbonCopy = {"chenxt0910@sina.cn"};
        String subject = "Universe注册验证";
        StringBuilder content = new StringBuilder();
        content.append("Universe注册验证码：");
        content.append(VerificationUtil.getRandNum(6)).append(" 。");
        content.append("验证码15分钟内有效！");

        try {
            mailUtil.sendSimpleEmail(deliver, receiver, carbonCopy, subject, content.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/test")
    @ResponseBody
    public Timestamp testTimestamp() {
        Date date = new Date();
        Timestamp t = new Timestamp(date.getTime());
        return t;
    }

}
