package com.seu.universe.service.Impl;

import com.alibaba.fastjson.JSON;
import com.seu.universe.config.ViewObject;
import com.seu.universe.entity.Comment;
import com.seu.universe.mapper.CommentMapper;
import com.seu.universe.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;


@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Override
    public ViewObject addComment(Comment comment) {
        ViewObject vo = new ViewObject();
        comment.setStatus(0);
        Date date = new Date();
        Timestamp time = new Timestamp(date.getTime());
        comment.setTime(time);
        commentMapper.addComment(comment);
        vo.set(ViewObject.ERROR, 0).set(ViewObject.MESSAGE, "评论成功！");
        return vo;
    }

    @Override
    public ViewObject getCommentByMessageId(long messageId) {
        ViewObject vo = new ViewObject();
        List<Comment> commentList = commentMapper.getCommentByMessageId(messageId);
        vo.set(ViewObject.ERROR, 0).set(ViewObject.DATA, JSON.toJSON(commentList));
        return vo;
    }

    @Override
    public ViewObject deleteComment(long commentId) {
        ViewObject vo = new ViewObject();
        commentMapper.deleteComment(commentId);
        vo.set(ViewObject.ERROR, 0).set(ViewObject.MESSAGE, "评论删除成功！");
        return vo;
    }
}
