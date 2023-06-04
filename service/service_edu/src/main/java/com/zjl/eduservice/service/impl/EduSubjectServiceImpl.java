package com.zjl.eduservice.service.impl;

import com.alibaba.excel.EasyExcel;
import com.zjl.eduservice.entity.EduSubject;
import com.zjl.eduservice.entity.excel.SubjectData;
import com.zjl.eduservice.listener.SubjectExcelListener;
import com.zjl.eduservice.mapper.EduSubjectMapper;
import com.zjl.eduservice.service.EduSubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

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
    public void saveSubject(MultipartFile file,EduSubjectService eduSubjectService) {

        try {
            //文件输入流
            InputStream in = file.getInputStream();
            //调用方法进行读取
            EasyExcel.read(in, SubjectData.class,new SubjectExcelListener(eduSubjectService)).sheet().doRead();

        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
