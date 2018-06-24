package com.seu.universe.controller;

import com.seu.universe.config.ViewObject;
import com.seu.universe.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/message")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @RequestMapping("/publish")
    @ResponseBody
    public ViewObject publishMessage(@RequestParam("messageType") String messageType,
                                     @RequestParam("messageInfo") String messageInfo,
                                     @RequestParam("label") String label,
                                     @RequestParam("pictureId") long pictureId,
                                     @RequestParam("userId") long userId) {
        ViewObject vo = messageService.publishMessage(messageType, messageInfo, label, pictureId, userId);
        return vo;
    }

    @RequestMapping("/getMessageById")
    @ResponseBody
    public ViewObject getMessageById(@RequestParam("messageId") long messageId,
                                 @RequestParam("userId") long userId) {
        ViewObject vo = messageService.getMessageByMessageId(messageId, userId);
        return vo;
    }

    @RequestMapping("/getRelatedMessageWithPage")
    @ResponseBody
    public ViewObject getRelatedMessageWithPage(@RequestParam("userId") long userId,
                                     @RequestParam("pageNum") int pageNum,
                                     @RequestParam("pageSize") int pageSize) {
        ViewObject vo = messageService.getMessageByUserIdWithPage(userId, pageNum, pageSize);
        return vo;
    }

    @RequestMapping("/getMessageByUserIdWithPage")
    @ResponseBody
    public ViewObject getMessageByUserIdWithPage(@RequestParam("userId") long userId,
                                                 @RequestParam("pageNum") int pageNum,
                                                 @RequestParam("pageSize") int pageSize) {
        ViewObject vo = messageService.getMessageByUserIdWithPage(userId, pageNum, pageSize);
        return vo;
    }
}
