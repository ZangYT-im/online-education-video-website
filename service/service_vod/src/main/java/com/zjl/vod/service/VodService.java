package com.zjl.vod.service;

import org.springframework.web.multipart.MultipartFile;

public interface VodService {
    String uploadVideoAliyun(MultipartFile file);
}
