package com.seu.universe.controller;

import com.seu.universe.config.ViewObject;
import com.seu.universe.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/like")
public class LikeController {

    @Autowired
    private LikeService likeService;

    @RequestMapping("/addLike")
    @ResponseBody
    public ViewObject addMessageLike(@RequestParam("messageId") long messageId,
                                     @RequestParam("messageUserId") long messageUserId,
                                     @RequestParam("userLikeId") long userLikeId) {
        ViewObject vo = likeService.addLikeToRedis(messageId, messageUserId, userLikeId);
        return vo;
    }

}
