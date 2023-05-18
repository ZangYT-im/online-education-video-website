package com.zjl.eduservice.controller;


import com.zjl.eduservice.entity.EduTeacher;
import com.zjl.eduservice.service.EduTeacherService;
import com.zjl.eduservice.service.impl.EduTeacherServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author zjl
 * @since 2023-05-18
 */
@RestController
@RequestMapping("/eduservice/edu-teacher")
public class EduTeacherController {

    //把service注入
    @Autowired
    private EduTeacherService teacherService;


    //1 查询讲师所有数据
    //restful风格
    @GetMapping("findAll")
    public List<EduTeacher> findAllTeacher(){
        //调用service查询所有
        List<EduTeacher> list = teacherService.list(null);
        return list;
    }


    //2逻辑删除讲师
    @DeleteMapping("{id}")
    public boolean removeTeacher(@PathVariable String id){
        return  teacherService.removeById(id);
    }
}

