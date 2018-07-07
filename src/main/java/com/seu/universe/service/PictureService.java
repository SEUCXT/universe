package com.seu.universe.service;

import com.seu.universe.config.ViewObject;

public interface PictureService {

    ViewObject addPicture(String pictureUrl, long userId);

    ViewObject deletePicture(long pictureId);

}
