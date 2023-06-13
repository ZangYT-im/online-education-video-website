package com.zjl.eduservice.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zjl.eduservice.entity.EduVideo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 课程视频 Mapper 接口
 * </p>
 *
 * @author zjl
 * @since 2023-06-05
 */
public interface EduVideoMapper extends BaseMapper<EduVideo> {

    void selectList(QueryWrapper<Object> wrapperVideo);
}
