package com.zjl.educenter.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zjl.educenter.utils.ConstantWxUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URLEncoder;

/**
 * @User: zangj
 * @Author: zjl
 * @Date: 2023/6/17
 * @Time: 23:06
 * @project:college_parent
 */

@CrossOrigin
@Controller//只是请求地址,不需要返回数据
@RequestMapping("/api/ucenter/wx")
public class WxApiController {

    //1 生成微信扫描二维码
    @GetMapping("login")
    public String getWxCode(){




//        String url = "https://open.weixin.qq.com/connect/qrconnect?appid=" + ConstantWxUtils.WX_OPEN_APP_ID + "&response_type";

        // 微信开放平台授权baseUrl
        String baseUrl = "https://open.weixin.qq.com/connect/qrconnect" +
                "?appid=%s" +
                "&redirect_uri=%s" +
                "&response_type=code" +
                "&scope=snsapi_login" +
                "&state=%s" +
                "#wechat_redirect";

        //对redirect_url进行URLEncoder编码
        String redirectUrl = ConstantWxUtils.WX_OPEN_REDIRECT_URL;
        try {
            redirectUrl = URLEncoder.encode(redirectUrl,"utf-8");
        }catch (Exception e) {
            e.printStackTrace();
        }
        //设置%s里面的值
        String url = String.format(
                baseUrl,
                ConstantWxUtils.WX_OPEN_APP_ID,
                ConstantWxUtils.WX_OPEN_REDIRECT_URL,
                "atguigu"
        );

        //重定向到请求微信地址
        return "redirect:"+url;
    }
}
