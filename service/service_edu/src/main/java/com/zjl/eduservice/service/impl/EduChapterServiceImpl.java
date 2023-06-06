package com.zjl.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zjl.eduservice.entity.EduChapter;
import com.zjl.eduservice.entity.EduVideo;
import com.zjl.eduservice.entity.chapter.ChapterVo;
import com.zjl.eduservice.entity.chapter.VideoVo;
import com.zjl.eduservice.mapper.EduChapterMapper;
import com.zjl.eduservice.service.EduChapterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zjl.eduservice.service.EduVideoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author zjl
 * @since 2023-06-05
 */
@Service
public class EduChapterServiceImpl extends ServiceImpl<EduChapterMapper, EduChapter> implements EduChapterService {

    //注入小节的service
    @Autowired
    private EduVideoService videoService;

    //课程大纲列表,根据课程id进行查询
    @Override
    public List<ChapterVo> getChapterVideoByCourseId(String courseId) {

        //1 根据课程id查询课程里面所有章节
        QueryWrapper<EduChapter> wrapperChapter = new QueryWrapper<>();
        wrapperChapter.eq("course_id",courseId);
        List<EduChapter> eduChapterList = baseMapper.selectList(wrapperChapter);

        //2 查询小节
        QueryWrapper<EduVideo> wrapperVideo = new QueryWrapper<>();
        wrapperVideo.eq("course_id",courseId);
        List<EduVideo> eduVideoList = videoService.list(wrapperVideo);


        //创建list集合，用于最终封装数据
        ArrayList<ChapterVo> finalList = new ArrayList<>();
        //3 遍历查询章节list集合进行封装
        for (int i = 0; i < eduChapterList.size(); i++) {

            //每个章节
            EduChapter eduChapter = eduChapterList.get(i);
            //eduChapter对象值复制到ChapterVo里面
            ChapterVo chapterVo = new ChapterVo();
            BeanUtils.copyProperties(eduChapter,chapterVo);
            //把chatperVo放到最终list集合
            finalList.add(chapterVo);

            //创建集合，用于封装章节的小节
            ArrayList<VideoVo> videoList = new ArrayList<>();
            //4 遍历查询小节list集合，进行封装
            for (int j = 0; j < eduVideoList.size(); j++) {

                EduVideo eduVideo = eduVideoList.get(j);
                //判断：小节里面chapterId和章节里面id是否一样
                if (eduVideo.getChapterId().equals(eduChapter.getId())){
                    //进行封装
                    VideoVo videoVo = new VideoVo();
                    BeanUtils.copyProperties(eduVideo,videoVo);
                    //放到小节封装集合
                    videoList.add(videoVo);
                }
            }
            //把封装之后小节list集合，放到章节对象里面
            chapterVo.setChildren(videoList);
        }
        return finalList;
    }
}
