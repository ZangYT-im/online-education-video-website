package com.zjl.eduservice.entity.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

/**
 * @User: zangj
 * @Author: zjl
 * @Date: 2023/6/8
 * @Time: 16:17
 * @project:college_parent
 */

@ApiModel(value = "课程发布信息")
@Data
public class CoursePublishVo {

    private String id;
    private String title;
    private String cover;
    private Integer lessonNum;
    private String subjectLevelOne;
    private String subjectLevelTwo;
    private String teacherName;
    private String price;//只用于显示
}
