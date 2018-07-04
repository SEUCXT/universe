package com.seu.universe.service;

import com.seu.universe.config.ViewObject;

public interface MessageService {

    /**
     * 发表状态
     * @param messageType
     * @param messageInfo
     * @param label
     * @param pictureId
     * @param userId
     * @return
     */
    ViewObject publishMessage(String messageType, String messageInfo, String label, long pictureId, long userId);

    /**
     * 获取状态
     * @param messageId
     * @param userId
     * @return
     */
    ViewObject getMessageByMessageId(Long messageId, long userId);

    /**
     * 分页读取状态
     * @param userId
     * @param pageNum
     * @param pageSize
     * @return
     */
    ViewObject getRelatedMessageWithPage(long userId, int pageNum, int pageSize);

    /**
     * 根据用户读取状态
     * @param userId
     * @param pageNum
     * @param pageSize
     * @return
     */
    ViewObject getMessageByUserIdWithPage(long userId, int pageNum, int pageSize);

    /**
     * 删除状态
     * @param userId
     * @param messageId
     * @return
     */
    ViewObject deleteMessage(long userId, long messageId);

    /**
     * 转发消息
     * @param messageId
     * @param userId
     * @return
     */
    ViewObject transpondMessage(long messageId, long userId);

}
