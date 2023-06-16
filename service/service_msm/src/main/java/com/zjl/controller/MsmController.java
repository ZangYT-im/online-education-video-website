package com.zjl.controller;

import com.zjl.commonutils.R;
import com.zjl.service.MsmService;
import com.zjl.utils.RandomUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @User: zangj
 * @Author: zjl
 * @Date: 2023/6/15
 * @Time: 16:45
 * @project:college_parent
 */

@RestController
@CrossOrigin
@RequestMapping("/edumsm/msm")
public class MsmController {


    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private MsmService msmService;

    //发送短信的方法
    @GetMapping("send/{phone}")
    public R sendMsm(@PathVariable String phone) {

        //1 从redis获取验证码,如果获取到直接返回
        String code = redisTemplate.opsForValue().get(phone);
        if (!StringUtils.isEmpty(code))
            return R.ok();


        //2 如果redis获取不到,进行阿里云发送
        //生成随机值,传递阿里云进行校验
        code = RandomUtil.getFourBitRandom();
        Map<String, Object> param = new HashMap<>();
        param.put("code", code);
        boolean isSend = msmService.send(param, phone);

        if (isSend) {
            //发送成功,把发送成功验证码发到redis里面
            //设置有效时间,5分钟有效
            redisTemplate.opsForValue().set(phone,code,5, TimeUnit.MINUTES);
            return R.ok();
        } else {
            return R.error().message("短信发送失败");
        }
    }

}
