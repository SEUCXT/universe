package com.seu.universe.mapper;

import com.seu.universe.entity.Picture;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PictureMapper {

    /**
     * 添加图片
     * @param picture
     */
    void addPicture(Picture picture);

    /**
     * 根据获取图片
     * @param pictureId
     * @return
     */
    Picture getPictureById(long pictureId);

    /**
     * 根据URL获取图片
     * @param pictureUrl
     * @return
     */
    Picture getPictureByUrl(String pictureUrl);

    /**
     * 删除图片
     * @param pictureId
     */
    void deletePicture(long pictureId);
}
