package com.seu.universe.service.Impl;

import com.seu.universe.config.ViewObject;
import com.seu.universe.entity.MessageTranspondCnt;
import com.seu.universe.mapper.TranspondCntMapper;
import com.seu.universe.service.TranspondService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TranspondServiceImpl implements TranspondService {

    @Autowired
    private TranspondCntMapper transpondCntMapper;

    @Override
    public ViewObject addTranspond(long messageId, long userId) {
        ViewObject vo = new ViewObject();
        MessageTranspondCnt messageTranspondCnt = transpondCntMapper.getMessageTranspondCnt(messageId);
        transpondCntMapper.updateTranspondLikeCnt(messageId, messageTranspondCnt.getTranspondCnt() + 1);
        vo.set(ViewObject.ERROR, 0).set(ViewObject.MESSAGE, "转发成功");
        return vo;
    }

    @Override
    public ViewObject getMessageTranspondCnt(long messageId) {
        ViewObject vo = new ViewObject();
        MessageTranspondCnt messageTranspondCnt = transpondCntMapper.getMessageTranspondCnt(messageId);
        vo.set(ViewObject.ERROR, 0).set(ViewObject.DATA, messageTranspondCnt.getTranspondCnt());
        return vo;
    }
}
