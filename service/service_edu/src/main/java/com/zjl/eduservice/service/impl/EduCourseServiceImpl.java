package com.zjl.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zjl.eduservice.entity.EduCourse;
import com.zjl.eduservice.entity.EduCourseDescription;
import com.zjl.eduservice.entity.EduTeacher;
import com.zjl.eduservice.entity.frontvo.CourseFrontVo;
import com.zjl.eduservice.entity.frontvo.CourseWebVo;
import com.zjl.eduservice.entity.vo.CourseInfoVo;
import com.zjl.eduservice.entity.vo.CoursePublishVo;
import com.zjl.eduservice.mapper.EduCourseMapper;
import com.zjl.eduservice.service.EduChapterService;
import com.zjl.eduservice.service.EduCourseDescriptionService;
import com.zjl.eduservice.service.EduCourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zjl.eduservice.service.EduVideoService;
import com.zjl.servicebase.exceptionhandler.CollegeException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author zjl
 * @since 2023-06-05
 */
@Service
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse> implements EduCourseService {

    //课程描述的注入
    @Autowired
    private EduCourseDescriptionService courseDescriptionService;


    //注入小节和章节service
    @Autowired
    private EduVideoService eduVideoService;

    @Autowired
    private EduChapterService eduChapterService;

    //添加课程基本信息
    @Override
    public String saveCourseInfo(CourseInfoVo courseInfoVo) {

        //1 向课程表添加课程基本信息
        //CourseInfoVo对象转换eduCourse对象
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfoVo,eduCourse);
        int insert = baseMapper.insert(eduCourse);
        if (insert==0){
            //添加失败
            throw new CollegeException(20001,"添加课程信息失败");
        }
        //获取添加之后课程id
        String cid = eduCourse.getId();
        //2 向课程简介表添加课程简介
        //edu_course_description
        EduCourseDescription courseDescription = new EduCourseDescription();
        courseDescription.setDescription(courseInfoVo.getDescription());

        //设置描述id就是课程id
        courseDescription.setId(cid);
        courseDescriptionService.save(courseDescription);

        return cid;

    }

    @Override
    public CourseInfoVo getCourseInfo(String courseId) {

        //1查询课程表
        EduCourse eduCourse = baseMapper.selectById(courseId);
        CourseInfoVo courseInfoVo = new CourseInfoVo();
        BeanUtils.copyProperties(eduCourse,courseInfoVo);


        //2查询描述表
        EduCourseDescription courseDescription = courseDescriptionService.getById(courseId);
        courseInfoVo.setDescription(courseDescription.getDescription());

        return courseInfoVo;
    }
    //修改课程信息
    @Override
    public void updateCourseInfo(CourseInfoVo courseInfoVo) {
        //1 修改课程表
        EduCourse eduCourse= new EduCourse();
        BeanUtils.copyProperties(courseInfoVo,eduCourse);

        int update = baseMapper.updateById(eduCourse);
        if (update==0){
            throw new CollegeException(20001,"修改课程信息失败");
        }

        //2修改描述表
        EduCourseDescription description = new EduCourseDescription();
        description.setId(courseInfoVo.getId());
        description.setDescription(courseInfoVo.getDescription());
        courseDescriptionService.updateById(description);
    }

    @Override
    public CoursePublishVo publishCourseInfo(String id) {
        //调用mapper
        CoursePublishVo publishCourseInfo = baseMapper.getPublishCourseInfo(id);
        return publishCourseInfo;

    }
    //删除课程
    @Override
    public void removeCourse(String courseId) {
        //1根据课程id删除小节
        eduVideoService.removeVideoByCourseId(courseId);

        //2 根据课程id删除章节

        eduChapterService.removeChapterByCourseId(courseId);

        //3 根据课程id删除描述
        courseDescriptionService.removeById(courseId);


        //4 根据课程id删除课程本身
        int result = baseMapper.deleteById(courseId);
        if (result==0){
            throw new CollegeException(20001,"删除失败");
        }
    }
    //1 条件查询带分页查询课程
    @Override
    public Map<String, Object> getCourseFrontList(Page<EduCourse> pageCourse, CourseFrontVo courseFrontVo) {

        //2 根据讲师id查询所有课程
        QueryWrapper<EduCourse> wrapper = new QueryWrapper<>();

        //判断条件值是否为空,不为空拼接
        if (!StringUtils.isEmpty(courseFrontVo.getSubjectParentId())){//一级分类
            //一级分类
            wrapper.eq("subject_parent_id",courseFrontVo.getSubjectParentId()   );
        }

        //判断条件值是否为空,不为空拼接
        if (!StringUtils.isEmpty(courseFrontVo.getSubjectId())){//二级分类
            //一级分类
            wrapper.eq("subject_id",courseFrontVo.getSubjectId()   );
        }

        //判断条件值是否为空,不为空拼接
        if (!StringUtils.isEmpty(courseFrontVo.getBuyCountSort())){//关注度
            //一级分类
            wrapper.orderByDesc("buy_count");
        }

        //判断条件值是否为空,不为空拼接
        if (!StringUtils.isEmpty(courseFrontVo.getGmtCreateSort())){//最新时间
            //一级分类
            wrapper.orderByDesc("gmt_create");
        }
        if (!StringUtils.isEmpty(courseFrontVo.getPriceSort())){//价格
            //一级分类
            wrapper.orderByDesc("price");
        }
        baseMapper.selectPage(pageCourse,wrapper);

        List<EduCourse> records = pageCourse.getRecords();
        long current = pageCourse.getCurrent();
        long pages = pageCourse.getPages();
        long size = pageCourse.getSize();
        long total = pageCourse.getTotal();


        boolean hasNext = pageCourse.hasNext();//下一页
        boolean hasPrevious = pageCourse.hasPrevious();//上一页
        //把分页数据获取出来,放到map集合
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("items", records);
        map.put("current", current);
        map.put("pages", pages);
        map.put("size", size);
        map.put("total", total);
        map.put("hasNext", hasNext);
        map.put("hasPrevious", hasPrevious);



        return map;
    }

    @Override
    public CourseWebVo getBaseCourseInfo(String courseId) {

        return baseMapper.getBaseCourseInfo(courseId);
    }
}
