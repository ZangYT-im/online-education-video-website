package com.zjl.eduservice.client;


import com.zjl.commonutils.R;
import com.zjl.servicebase.exceptionhandler.CollegeException;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Component
@FeignClient(name = "service-vod",
        fallback = VodFileDegradeFeignClient.class)//服务名字
public interface VodClient {

    //定义调用的方法路径
    //根据视频id删除阿里云视频
    @DeleteMapping("/eduvod/video/removeAliyunVideo/{id}")
    public R removeAlyVideo(@PathVariable("id") String id);//必须要注解变量名称

    //删除多个阿里云视频
    //参数是多个视频id
    @DeleteMapping("/eduvod/video/delete-batch")
    public R deleteBatch(@RequestParam("videoIdList") List<String> videoIdList);

}
