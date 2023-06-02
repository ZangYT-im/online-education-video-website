package com.zjl.oss.controller;

import com.zjl.commonutils.R;
import com.zjl.oss.service.OssService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @User: zangj
 * @Author: zjl
 * @Date: 2023/5/26
 * @Time: 22:59
 * @project:college_parent
 */

@RestController
@RequestMapping("/eduoss/fileoss")
@CrossOrigin
public class OssController {

    @Autowired
    private OssService ossService;


    //上传头像的方法
    @PostMapping
    public R uploadOssFile(MultipartFile file){
        //获取上传文件 MultipartFile
        //返回上传到oss的路径
        String url = ossService.uploadFileAvatar(file);
        return R.ok().data("url",url);
    }
}
