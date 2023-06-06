package com.zjl.eduservice.entity.chapter;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @User: zangj
 * @Author: zjl
 * @Date: 2023/6/6
 * @Time: 14:17
 * @project:college_parent
 */

@Data
public class ChapterVo {

    private String id;

    private  String title;

    //表示小节
    private List<VideoVo> children = new ArrayList<>();
}
