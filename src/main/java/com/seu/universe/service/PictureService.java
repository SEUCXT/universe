package com.seu.universe.service;

import com.seu.universe.config.ViewObject;
import com.seu.universe.entity.Picture;

public interface PictureService {

    ViewObject addPicture(String pictureUrl, long userId);

    ViewObject deletePicture(long pictureId);

    Picture getPictureById(long pictureId);

    Picture getPictureByUrl(String pictureUrl);

}
