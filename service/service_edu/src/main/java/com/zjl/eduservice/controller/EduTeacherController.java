package com.zjl.eduservice.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zjl.commonutils.R;
import com.zjl.eduservice.entity.EduTeacher;
import com.zjl.eduservice.service.EduTeacherService;
import com.zjl.eduservice.service.impl.EduTeacherServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.poi.ss.usermodel.CellStyle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author zjl
 * @since 2023-05-18
 */


@Api(description = "讲师管理")
@RestController
@RequestMapping("/eduservice/edu-teacher")
public class EduTeacherController {

    //把service注入
    @Autowired
    private EduTeacherService teacherService;


    //1 查询讲师所有数据
    //restful风格
    @GetMapping("findAll")
    @ApiOperation(value = "所有讲师列表")
    public R findAllTeacher() {
        //调用service查询所有
        List<EduTeacher> list = teacherService.list(null);
        return R.ok().data("items",list);
    }


    //2逻辑删除讲师
    @DeleteMapping("{id}")
    @ApiOperation(value = "逻辑删除所有讲师")
    public R removeTeacher(@ApiParam(name = "id", value = "讲师id", required = true) @PathVariable String id) {
        boolean flag = teacherService.removeById(id);
        if (flag)
            return R.ok();
        else
            return R.error();
    }


    //3 分页查询方法
    //current 当前页
    //limit 每页记录数
    @GetMapping("pageTeacher/{current}/{limit}")
    public R pageListTeacher(@PathVariable long current ,
                             @PathVariable long limit){

        //创建page对象
        Page<EduTeacher> pageTeacher = new Page<>(current,limit);

        //调用方法实现分页
        //调用方法的时候，底层做了分装，把分页所有的数据封装到pageTeacher对象里面
        teacherService.page(pageTeacher,null);
        long total = pageTeacher.getTotal();
        List<EduTeacher> records = pageTeacher.getRecords();

//        HashMap map = new HashMap();
//        map.put("total",total);
//        map.put("row",records);

        return R.ok().data("total",total).data("row",records);


    }
}

