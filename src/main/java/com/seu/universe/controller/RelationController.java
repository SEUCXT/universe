package com.seu.universe.controller;

import com.seu.universe.config.ViewObject;
import com.seu.universe.service.RelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/relation")
public class RelationController {

    @Autowired
    private RelationService relationService;

    @RequestMapping("/addRelation")
    @ResponseBody
    public ViewObject addUserRelation(@RequestParam("relationType") String relationType,
                                      @RequestParam("relationGroup") String relationGroup,
                                      @RequestParam("userId") long userId,
                                      @RequestParam("userById") long userById) {
        ViewObject vo = relationService.addRelation(relationType, relationGroup, userId, userById);
        return vo;
    }

    @RequestMapping("getRelationByUserId")
    @ResponseBody
    public ViewObject getRelation(@RequestParam("userId") long userId) {
        ViewObject vo = relationService.getRelationByUserId(userId);
        return vo;
    }

    @RequestMapping("/getRelationBetweenUsers")
    @ResponseBody
    public ViewObject  getRelationBetweenUsers(@RequestParam("userId") long userId,
                                               @RequestParam("userById") long userById) {
        ViewObject vo = relationService.getRelationBetweenUsers(userId, userById);
        return vo;
    }
}
