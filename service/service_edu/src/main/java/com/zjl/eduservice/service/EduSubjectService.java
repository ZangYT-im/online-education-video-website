package com.zjl.eduservice.service;

import com.zjl.eduservice.entity.EduSubject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zjl.eduservice.entity.subject.OneSubject;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author zjl
 * @since 2023-06-02
 */
public interface EduSubjectService extends IService<EduSubject> {

    //添加课程分类
    void saveSubject(MultipartFile file,EduSubjectService eduSubjectService);

    List<OneSubject> getAllOneTwoSubject();
}
