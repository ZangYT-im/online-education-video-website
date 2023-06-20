package com.zjl.eduservice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zjl.eduservice.entity.EduCourse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zjl.eduservice.entity.frontvo.CourseFrontVo;
import com.zjl.eduservice.entity.frontvo.CourseWebVo;
import com.zjl.eduservice.entity.vo.CourseInfoVo;
import com.zjl.eduservice.entity.vo.CoursePublishVo;

import java.util.Map;

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
    //1 条件查询带分页查询课程
    Map<String, Object> getCourseFrontList(Page<EduCourse> pageCourse, CourseFrontVo courseFrontVo);
    //根据课程id,编写sql语句查询课程信息
    CourseWebVo getBaseCourseInfo(String courseId);
}
