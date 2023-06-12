package com.zjl.eduservice.controller;


import com.zjl.commonutils.R;
import com.zjl.eduservice.entity.EduVideo;
import com.zjl.eduservice.service.EduVideoService;
import org.springframework.beans.factory.annotation.Autowired;
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



    @Autowired
    private EduVideoService videoService;

    //添加小节
    @PostMapping("addVideo")
    public R addVideo(@RequestBody EduVideo eduVideo){
        videoService.save(eduVideo);
        return R.ok();
    }


    //删除小节
    //todo 后面这个方法需要完善，删除小节时，同时要把里面视频删除
    @DeleteMapping("{id}")
    public R deleteVideo(@PathVariable String id){
        videoService.removeById(id);
        return R.ok();
    }


    //修改小节 todo

}

