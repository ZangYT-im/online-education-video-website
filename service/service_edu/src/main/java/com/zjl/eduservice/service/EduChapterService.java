package com.zjl.eduservice.service;

import com.zjl.eduservice.entity.EduChapter;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zjl.eduservice.entity.chapter.ChapterVo;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author zjl
 * @since 2023-06-05
 */
public interface EduChapterService extends IService<EduChapter> {

    //课程大纲列表,根据课程id进行查询
    List<ChapterVo> getChapterVideoByCourseId(String courseId);
}
