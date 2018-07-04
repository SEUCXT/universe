package com.seu.universe.controller;


import com.seu.universe.config.ViewObject;
import com.seu.universe.entity.Comment;
import com.seu.universe.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @RequestMapping("delete")
    @ResponseBody
    public ViewObject deleteComment(@RequestParam("commentId") long commentId) {

        ViewObject vo = commentService.deleteComment(commentId);
        return vo;
    }

    @RequestMapping("getAll")
    @ResponseBody
    public ViewObject getAllComment(@RequestParam("messageId") long messageId) {

        ViewObject vo = commentService.getCommentByMessageId(messageId);
        return vo;
    }

    @RequestMapping("add")
    @ResponseBody
    public ViewObject addComment(@RequestBody Comment comment) {

        ViewObject vo = commentService.addComment(comment);
        return vo;
    }

}
