package com.zjl.eduservice.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zjl.eduservice.entity.EduSubject;
import com.zjl.eduservice.entity.excel.SubjectData;
import com.zjl.eduservice.entity.subject.OneSubject;
import com.zjl.eduservice.entity.subject.TwoSubject;
import com.zjl.eduservice.listener.SubjectExcelListener;
import com.zjl.eduservice.mapper.EduSubjectMapper;
import com.zjl.eduservice.service.EduSubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author zjl
 * @since 2023-06-02
 */
@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService {


    //添加课程分类
    @Override
    public void saveSubject(MultipartFile file, EduSubjectService eduSubjectService) {

        try {
            //文件输入流
            InputStream in = file.getInputStream();
            //调用方法进行读取
            EasyExcel.read(in, SubjectData.class, new SubjectExcelListener(eduSubjectService)).sheet().doRead();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //课程分类列表（树形）
    @Override
    public List<OneSubject> getAllOneTwoSubject() {

        //1 查询所有一级分类  parentId = 0
        QueryWrapper<EduSubject> wrapperOne = new QueryWrapper<>();
        wrapperOne.eq("parent_id", "0");
        List<EduSubject> oneSubjectsList = baseMapper.selectList(wrapperOne);

//        this.list(wrapperOne)
        //2 查询所有二级分类  parentId != 0
        QueryWrapper<EduSubject> wrapperTwo = new QueryWrapper<>();
        wrapperTwo.ne("parent_id","0");
        List<EduSubject> twoSubjectsList = baseMapper.selectList(wrapperTwo);

        //创建list集合，用于存储最终封装的数据
        List<OneSubject> finalSubjectList = new ArrayList<>();


        //3 封装一级分类
        //查询出来所有的一级分类list集合遍历，得到每个一级分类对象，获取每个一级分类对象
        //封装到要求的list集合里面list<oneSubject> finalSubjectList
        for (int i = 0; i < oneSubjectsList.size(); i++) {//遍历

            EduSubject eduSubject = oneSubjectsList.get(i);

            //把eduSubject里面值获取出来，放到oneSubject对象里面
            //多个OneSubject放到finalSubjectList里面
            OneSubject oneSubject  = new OneSubject();
//            oneSubject.setId(eduSubject.getId());
//            oneSubject.setTitle(eduSubject.getTitle());

            //eduSubject值复制到对应oneSubject
            BeanUtils.copyProperties(eduSubject, oneSubject);

            finalSubjectList.add(oneSubject);

            //在一级分类循环遍历查询所有的二级分类
            //创建list集合封装每个一级分类的二级分类
            List<TwoSubject> twoFinalSubjectList = new ArrayList<>();

            //遍历二级分类list集合
            for (int j = 0; j < twoSubjectsList.size(); j++) {
                //获取每个二级分类
                EduSubject tSubject = twoSubjectsList.get(j);

                //判断二级分类parentId和一级分类id是否一样
                if (tSubject.getParentId().equals(eduSubject.getId())){
                        //把tSubject值复制到TwoSubject里面，放到twoFinalSunjectList里面
                    TwoSubject twoSubject = new TwoSubject();
                    BeanUtils.copyProperties(tSubject,twoSubject);
                    twoFinalSubjectList.add(twoSubject);
                }
            }
            //把一级下面所有二级分类放到一级分类里面
            oneSubject.setChildren(twoFinalSubjectList);
        }
        return finalSubjectList;
        //4 封装二级分类

    }
}
