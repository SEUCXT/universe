package com.seu.universe.mapper;


import com.seu.universe.entity.MessageTranspondCnt;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface TranspondCntMapper {

    MessageTranspondCnt getMessageTranspondCnt(long messageId);

    int addMessageTranspondCnt(@Param("messageId") long messageId,
                               @Param("transpondCnt") long transpondCnt);

    int updateTranspondLikeCnt(@Param("messageId") long messageId,
                               @Param("newTranspondCnt") long newTranspondCnt);
}
