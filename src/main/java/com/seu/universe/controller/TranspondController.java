package com.seu.universe.controller;

import com.seu.universe.config.ViewObject;
import com.seu.universe.service.TranspondService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/transpond")
public class TranspondController {

    @Autowired
    private TranspondService transpondService;

    @RequestMapping("addTranspond")
    @ResponseBody
    public ViewObject addTranspond(@RequestParam("messageId") long messageId,
                                   @RequestParam("userId") long userId) {
        return transpondService.addTranspond(messageId, userId);
    }

    @RequestMapping("getTranspondCnt")
    @ResponseBody
    public ViewObject getTranspondCnt(@RequestParam("messageId") long messageId) {
        return transpondService.getMessageTranspondCnt(messageId);
    }
}
