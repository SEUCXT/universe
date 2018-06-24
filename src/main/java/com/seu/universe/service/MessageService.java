package com.seu.universe.service;

import com.seu.universe.config.ViewObject;

public interface MessageService {

    ViewObject publishMessage(String messageType, String messageInfo, String label, long pictureId, long userId);

    ViewObject getMessageByMessageId(Long messageId, long userId);

    ViewObject getRelatedMessageWithPage(long userId, int pageNum, int pageSize);

    ViewObject getMessageByUserIdWithPage(long userId, int pageNum, int pageSize);

    ViewObject deleteMessage(long userId, long messageId);

}
