package com.zjl.educms.controller;

import com.zjl.commonutils.R;
import com.zjl.educms.entity.CrmBanner;
import com.zjl.educms.service.CrmBannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @User: zangj
 * @Author: zjl
 * @Date: 2023/6/14
 * @Time: 16:26
 * @project:college_parent
 * @Description: 前台banner显示
 */

@CrossOrigin
@RequestMapping("/educms/bannerfront")
@RestController
public class BannerFrontController {

    @Autowired
    private CrmBannerService bannerService;


    //查询所有banner
    @GetMapping("getAllBanner")
    public R getAllBanner(){
      List<CrmBanner> list =  bannerService.selectAllBanner();
        return R.ok().data("list",list);
    }
}
