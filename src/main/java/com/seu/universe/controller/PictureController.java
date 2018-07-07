package com.seu.universe.controller;

import com.alibaba.fastjson.JSON;
import com.seu.universe.config.ViewObject;
import com.seu.universe.entity.Picture;
import com.seu.universe.mapper.PictureMapper;
import com.seu.universe.service.PictureService;
import com.seu.universe.utils.FastDFSUtil;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;

@Controller
@RequestMapping("/picture")
public class PictureController {

    @Autowired
    private PictureMapper pictureMapper;

    @Autowired
    private PictureService pictureService;

    @RequestMapping("/get")
    @ResponseBody
    public ResponseEntity<ViewObject> getPictureById(@RequestParam("pictureId") long pictureId) {
        ViewObject vo = new ViewObject();
        Picture picture = pictureMapper.getPictureById(pictureId);
        vo.set(ViewObject.ERROR, 0).set(ViewObject.DATA, JSON.toJSON(picture));
        return ResponseEntity.ok(vo);
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ViewObject> uploadPicture(@RequestParam("pictureFile") MultipartFile pictureFile,
                                                    @RequestParam("userId") long userId,
                                                    HttpServletRequest request) {
        ViewObject vo = new ViewObject();
        String path = request.getSession().getServletContext().getRealPath("upload");
        String fileName = pictureFile.getOriginalFilename();
        File dir = new File(path);
        File targetFile = new File(path, fileName);
        boolean flag = false;
        if (!dir.exists()) {
            dir.mkdirs();
        }
        //保存文件
        try {
            FileUtils.copyInputStreamToFile(pictureFile.getInputStream(), targetFile);
        } catch (Exception e) {
            e.printStackTrace();
        }

        int dotPos1 = fileName.lastIndexOf(".");
        String extName = fileName.substring(dotPos1 + 1);
        String pictureUrl = FastDFSUtil.uploadImage(targetFile.getAbsolutePath(), extName);
        pictureService.addPicture(pictureUrl, userId);
        Picture picture = pictureMapper.getPictureByUrl(pictureUrl);
        vo.set(ViewObject.ERROR, 0).set(ViewObject.DATA, JSON.toJSON(picture));
        return ResponseEntity.ok(vo);
    }
}
