package com.zjl.eduservice.controller.front;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zjl.commonutils.R;
import com.zjl.eduservice.entity.EduCourse;
import com.zjl.eduservice.entity.EduTeacher;
import com.zjl.eduservice.service.EduCourseService;
import com.zjl.eduservice.service.EduTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @User: zangj
 * @Author: zjl
 * @Date: 2023/6/19
 * @Time: 8:51
 * @project:college_parent
 */


@RestController
@CrossOrigin
@RequestMapping("/eduservice/teacherfront")
public class TeacherFrontController {

    @Autowired
    private EduTeacherService teacherService;

    @Autowired
    private EduCourseService courseService;

    //1 分页查询讲师的方法
    @PostMapping("getTeacherFront/{page}/{limit}")
    public R getTeacherFrontList(@PathVariable long page, @PathVariable long limit) {

        Page<EduTeacher> pageTeacher = new Page<>(page, limit);
        Map<String, Object> map = teacherService.getTeacherFrontList(pageTeacher);

        //返回分页所有数据
        return R.ok().data(map);
    }

    // 2讲师详情的功能
    @PostMapping("getTeacherFrontInfo/{teacherId}")
    public R getTeacherFrontInfo(@PathVariable String teacherId){
        //1 根据讲师id查询讲师基本信息
        EduTeacher eduTeacher = teacherService.getById(teacherId);

        //2 根据讲师id查询所有课程

        QueryWrapper<EduCourse> wrapper = new QueryWrapper<>();

        wrapper.eq("teacher_id",teacherId);
        List<EduCourse> courseList = courseService.list(wrapper);
        return R.ok().data("teacher",eduTeacher).data("courseList",courseList);
    }
}
