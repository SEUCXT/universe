package com.seu.universe.service.Impl;

import com.seu.universe.config.ViewObject;
import com.seu.universe.entity.Picture;
import com.seu.universe.mapper.PictureMapper;
import com.seu.universe.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;


@Service
public class PictureServiceImpl implements PictureService {

    @Autowired
    private PictureMapper pictureMapper;

    @Override
    public ViewObject addPicture(String pictureUrl, long userId) {
        ViewObject vo = new ViewObject();
        Picture picture = new Picture();
        picture.setPictureUrl(pictureUrl);
        picture.setUserId(userId);
        Date date = new Date();
        Timestamp time = new Timestamp(date.getTime());
        picture.setTime(time);
        pictureMapper.addPicture(picture);
        vo.set(ViewObject.DATA, 0).set(ViewObject.MESSAGE, "图片添加成功");
        return vo;
    }

    @Override
    public ViewObject deletePicture(long pictureId) {
        ViewObject vo = new ViewObject();
        pictureMapper.deletePicture(pictureId);
        vo.set(ViewObject.DATA, 0).set(ViewObject.MESSAGE, "删除图片成功！");
        return vo;
    }

    @Override
    public Picture getPictureById(long pictureId) {
        Picture picture = pictureMapper.getPictureById(pictureId);
        return picture;
    }

    @Override
    public Picture getPictureByUrl(String pictureUrl) {
        Picture picture = pictureMapper.getPictureByUrl(pictureUrl);
        return picture;
    }
}
