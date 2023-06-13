package com.zjl.vod.service;

import com.zjl.commonutils.R;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface VodService {
    String uploadVideoAliyun(MultipartFile file);

    //删除多个阿里云视频
    void removeMoreAliyunVideo(List videoIdList);



}
