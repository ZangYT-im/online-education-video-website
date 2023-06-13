package com.zjl.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zjl.eduservice.client.VodClient;
import com.zjl.eduservice.entity.EduVideo;
import com.zjl.eduservice.mapper.EduVideoMapper;
import com.zjl.eduservice.service.EduVideoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程视频 服务实现类
 * </p>
 *
 * @author zjl
 * @since 2023-06-05
 */
@Service
public class EduVideoServiceImpl extends ServiceImpl<EduVideoMapper, EduVideo> implements EduVideoService {

    @Autowired
    private VodClient vodClient;

    //todo 删除小节，删除对应视频文件
    @Override
    public void removeVideoByCourseId(String courseId) {

        //1 根据视课程id查询所有视频id
        QueryWrapper<EduVideo> wrapperVideo = new QueryWrapper<>();

        wrapperVideo.eq("course_id", courseId);
        wrapperVideo.select("video_source_id");
        List<EduVideo> eduVideoList = baseMapper.selectList(wrapperVideo);


        List<String> videoIds = new ArrayList<>();
        for (int i = 0; i < videoIds.size(); i++) {
            EduVideo eduVideo = eduVideoList.get(i);
            String videoSourceId = eduVideo.getVideoSourceId();

            if(!StringUtils.isEmpty(videoSourceId)) {
                //放到videoIds集合里面
                videoIds.add(videoSourceId);
            }
        }

        //根据多个视频id删除视频
        if(videoIds.size()>0) {
            vodClient.deleteBatch(videoIds);
        }

        QueryWrapper<EduVideo> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id", courseId);
        baseMapper.delete(wrapper);
    }
}
