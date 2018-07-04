package com.seu.universe.mapper;

import com.seu.universe.entity.MessageLikeCnt;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface LikeCntMapper {

    MessageLikeCnt getMessageLikeCnt(long messageId);

    int addMessageLikeCnt(@Param("messageId") long messageId,
                          @Param("likeCnt") long likeCnt);

     int updateMessageLikeCnt(@Param("messageId") long messageId,
                              @Param("newLikeCnt") long newLikeCnt);

}
