package com.seu.universe.service;

import com.seu.universe.config.ViewObject;

public interface TranspondService {

    /**
     * 添加转发
     *
     * @param messageId
     * @param userId
     * @return
     */
    ViewObject addTranspond(long messageId, long userId);

    /**
     * 获取状态的点赞数
     *
     * @param messageId
     * @return
     */
    ViewObject getMessageTranspondCnt(long messageId);

}
