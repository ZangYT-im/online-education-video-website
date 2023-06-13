package com.zjl.eduservice.client;

import com.zjl.commonutils.R;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @User: zangj
 * @Author: zjl
 * @Date: 2023/6/13
 * @Time: 15:31
 * @project:college_parent
 */
@Component
public class VodFileDegradeFeignClient implements VodClient{

    /*
    * 出错之后执行
    * */
    @Override
    public R removeAlyVideo(String id) {
        return R.error().message("删除视频出错");
    }

    @Override
    public R deleteBatch(List<String> videoIdList) {
        return  R.error().message("删除多个视频出错");
    }
}
