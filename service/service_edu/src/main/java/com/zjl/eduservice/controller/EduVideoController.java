package com.zjl.eduservice.controller;


import com.zjl.commonutils.R;
import com.zjl.eduservice.client.VodClient;
import com.zjl.eduservice.entity.EduVideo;
import com.zjl.eduservice.service.EduVideoService;
import com.zjl.servicebase.exceptionhandler.CollegeException;
import org.apache.poi.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author zjl
 * @since 2023-06-05
 */

@CrossOrigin
@RestController
@RequestMapping("/eduservice/video")
public class EduVideoController {

    //注入vodClient
    @Autowired
    private VodClient vodClient;


    @Autowired
    private EduVideoService videoService;

    //添加小节
    @PostMapping("addVideo")
    public R addVideo(@RequestBody EduVideo eduVideo) {
        videoService.save(eduVideo);
        return R.ok();
    }


    //删除小节
    //后面这个方法需要完善，删除小节时，同时要把里面视频删除
    @DeleteMapping("{id}")
    public R deleteVideo(@PathVariable String id) {
        //先根据小节id获取视频id，调用方法实现视频删除
        EduVideo eduVideo = videoService.getById(id);
        String videoSourceId = eduVideo.getVideoSourceId();

        //根据视频id远程调用视频删除
        if (!StringUtils.isEmpty(videoSourceId)) {
            R result = vodClient.removeAlyVideo(videoSourceId);
            if (result.getCode() == 20001){
                throw new CollegeException(20001,"删除视频失败，熔断器..");
            }
        }


        //删除小节
        videoService.removeById(id);
        return R.ok();
    }


    //修改小节 todo

}

