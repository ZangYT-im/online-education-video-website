package com.zjl.educenter.service;

import com.zjl.educenter.entity.UcenterMember;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zjl.educenter.entity.vo.RegisterVo;
import io.swagger.models.auth.In;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author zjl
 * @since 2023-06-16
 */
public interface UcenterMemberService extends IService<UcenterMember> {

    //登录方法
    String login(UcenterMember member);

    //注册
    void register(RegisterVo registerVo);

    //查询某一天注册人数
    Integer countRegisterDay(String day);
}
