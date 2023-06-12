package com.zjl.eduservice.service;

import com.zjl.eduservice.entity.EduCourse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zjl.eduservice.entity.vo.CourseInfoVo;
import com.zjl.eduservice.entity.vo.CoursePublishVo;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author zjl
 * @since 2023-06-05
 */
public interface EduCourseService extends IService<EduCourse> {
    //添加课程基本信息
    String saveCourseInfo(CourseInfoVo courseInfoVo);
    //根据课程查询课程基本信息
    CourseInfoVo getCourseInfo(String courseId);
    //修改课程信息
    void updateCourseInfo(CourseInfoVo courseInfoVo);


    CoursePublishVo publishCourseInfo(String id);
    //删除课程
    void removeCourse(String courseId);
}
