package com.seu.universe.service;

import com.seu.universe.config.ViewObject;
import com.seu.universe.entity.Comment;

public interface CommentService {

    ViewObject addComment(Comment comment);

    ViewObject getCommentByMessageId(long messageId);

    ViewObject deleteComment(long commentId);
}
