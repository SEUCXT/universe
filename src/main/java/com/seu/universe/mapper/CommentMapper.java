package com.seu.universe.mapper;

import com.seu.universe.entity.Comment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommentMapper {

    void addComment(Comment comment);

    List<Comment> getCommentByMessageId(long messageId);

    void deleteComment(long commentId);
}
