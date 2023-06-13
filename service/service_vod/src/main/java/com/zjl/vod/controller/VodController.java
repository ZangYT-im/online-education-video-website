package com.zjl.vod.controller;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest;
import com.zjl.commonutils.R;
import com.zjl.servicebase.exceptionhandler.CollegeException;
import com.zjl.vod.service.VodService;
import com.zjl.vod.utils.ConstantVodUtils;
import com.zjl.vod.utils.InitVodClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
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
    public R uploadAliyunVideo(MultipartFile file) {

        //返回上传视频id
        String videoId = vodService.uploadVideoAliyun(file);
        return R.ok().data("videoId", videoId);
    }

    //根据视频id删除阿里云视频
    @DeleteMapping("removeAliyunVideo/{id}")
    public R removeAlyVideo(@PathVariable String id) {

        try {

            //初始化对象
            DefaultAcsClient client = InitVodClient.initVodClient(
                    ConstantVodUtils.ACCESS_KEY_ID,
                    ConstantVodUtils.ACCESS_KEY_SECRET);
            //创建删除视频request对象
            DeleteVideoRequest request = new DeleteVideoRequest();
            //向request设置视频id
            request.setVideoIds(id);
            //调用初始化对象的方法实现删除
            client.getAcsResponse(request);
            return R.ok();
        } catch (Exception e) {
            e.printStackTrace();
            throw new CollegeException(20001, "删除视频失败");
        }
    }
}
