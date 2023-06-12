package com.zjl.vod.controller;

import com.zjl.commonutils.R;
import com.zjl.vod.service.VodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @User: zangj
 * @Author: zjl
 * @Date: 2023/6/12
 * @Time: 9:55
 * @project:college_parent
 */

@CrossOrigin
@RestController
@RequestMapping("/eduvod/video")
public class VodController {

    @Autowired
    private VodService vodService;

    //上传视频到阿里云
    @PostMapping("uploadAliyunVideo")
    public R uploadAliyunVideo(MultipartFile file){

        //返回上传视频id
       String videoId =  vodService.uploadVideoAliyun(file);
        return R.ok().data("videoId",videoId);
    }
}
