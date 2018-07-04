package com.seu.universe.mapper;

import com.seu.universe.entity.Message;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.sql.Timestamp;
import java.util.List;

@Mapper
public interface MessageMapper {

    void publishMessage(@Param("tableName") String tableName,
                        @Param("messageType") String messageType,
                        @Param("messageInfo") String messageInfo,
                        @Param("time") Timestamp time,
                        @Param("collectNum") int collectNum,
                        @Param("readNum") int readNum,
                        @Param("commentNum") int commentNum,
                        @Param("likeNum") int likeNum,
                        @Param("transpondNum") int transpondNum,
                        @Param("label") String label,
                        @Param("pictureId") long pictureId,
                        @Param("userId") long userId,
                        @Param("status") int status);

    Message getMessageByMessageId(@Param("tableName") String tableName,
                                  @Param("messageId") long messageId);

    List<Message> getRelatedMessageWithPage(@Param("tableName") String tableName,
                                            @Param("start") int start,
                                            @Param("limit") int limit);

    List<Message> getMessageByUserIdWithPage(@Param("tableName") String tableName,
                                             @Param("userId") long userId,
                                             @Param("start") int start,
                                             @Param("limit") int limit);

    int deleteMessage(@Param("tableName") String tableName,
                      @Param("messageId") long messageId);

}
