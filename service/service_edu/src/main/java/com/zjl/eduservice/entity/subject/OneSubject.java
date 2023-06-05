package com.zjl.eduservice.entity.subject;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @User: zangj
 * @Author: zjl
 * @Date: 2023/6/5
 * @Time: 8:57
 * @project:college_parent
 */


//一级分类
@Data
public class OneSubject {

    private String id;
    private String title;

    //一个一级分类里有多个二级分类
    private List<TwoSubject> children = new ArrayList<>();


}
