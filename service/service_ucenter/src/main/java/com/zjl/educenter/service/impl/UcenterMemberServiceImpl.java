package com.zjl.educenter.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zjl.commonutils.JwtUtils;
import com.zjl.commonutils.MD5;
import com.zjl.educenter.entity.UcenterMember;
import com.zjl.educenter.entity.vo.RegisterVo;
import com.zjl.educenter.mapper.UcenterMemberMapper;
import com.zjl.educenter.service.UcenterMemberService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zjl.servicebase.exceptionhandler.CollegeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * <p>
 * 会员表 服务实现类
 * </p>
 *
 * @author zjl
 * @since 2023-06-16
 */
@Service
public class UcenterMemberServiceImpl extends ServiceImpl<UcenterMemberMapper, UcenterMember> implements UcenterMemberService {


    @Autowired
    private RedisTemplate<String, String> redisTemplate;


    //登录方法
    @Override
    public String login(UcenterMember member) {

        //获取登录手机号和密码
        String mobile = member.getMobile();
        String password = member.getPassword();

        //手机号和密码非空的判断
        if (StringUtils.isEmpty(member) || StringUtils.isEmpty(password)) {
            throw new CollegeException(20001, "登录失败");
        }
        //判断手机号是否正确
        QueryWrapper<UcenterMember> wrapper = new QueryWrapper<>();
        wrapper.eq("mobile", mobile);

        UcenterMember mobileMember = baseMapper.selectOne(wrapper);
        //判断查询对象是否为空
        if (mobileMember == null) {
            //没有这个手机号
            throw new CollegeException(20001, "登录失败");
        }

        //判断密码
        //因为存储数据库里密码加密了
        //把输入密码加密,再和数据库里比较
        if (!MD5.encrypt(password).equals(mobileMember.getPassword())) {
            throw new CollegeException(20001, "登录失败");
        }

        //判断用户是否被禁用
        if (mobileMember.getIsDisabled()) {
            throw new CollegeException(20001, "登录失败");
        }

        //登录成功
        //生成token字符串,使用jwt工具类
        String jwtToken = JwtUtils.getJwtToken(mobileMember.getId(), mobileMember.getNickname());


        return jwtToken;
    }

    //注册
    @Override
    public void register(RegisterVo registerVo) {

        //获取注册的数据
        String code = registerVo.getCode();//验证码
        String mobile = registerVo.getMobile();//手机号
        String nickname = registerVo.getNickname();//昵称
        String password = registerVo.getPassword();//密码

        //非空的判断
        if (StringUtils.isEmpty(code) ||
                StringUtils.isEmpty(mobile) ||
                StringUtils.isEmpty(nickname) ||
                StringUtils.isEmpty(password)) {
            throw new CollegeException(20001, "注册失败");
        }

        //判断手机验证码
        //获取redis中的验证码
        String redisCode = redisTemplate.opsForValue().get(mobile);


        if (!code.equals(redisCode))
            throw new CollegeException(20001,"注册失败");


        //判断手机号是否重复,表里面存在相同手机号不进行添加
        QueryWrapper<UcenterMember> wrapper = new QueryWrapper<>();
        wrapper.eq("mobile",mobile);
        Integer count = baseMapper.selectCount(wrapper);
        if (count>0){
            throw new CollegeException(20001,"注册失败");
        }

        //数据添加到数据库中
        UcenterMember member = new UcenterMember();
        member.setMobile(mobile);
        member.setNickname(nickname);
        member.setPassword(MD5.encrypt(password));//
        member.setIsDeleted(false);//用户不禁用
//        member.setAvatar();
        baseMapper.insert(member);
    }

    //查询某一天注册人数
    @Override
    public Integer countRegisterDay(String day) {
        return baseMapper.countRegisterDay(day);
    }
}
