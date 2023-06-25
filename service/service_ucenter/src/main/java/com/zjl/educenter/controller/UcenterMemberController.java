package com.zjl.educenter.controller;


import com.zjl.commonutils.JwtUtils;
import com.zjl.commonutils.R;
import com.zjl.commonutils.ordervo.UcenterMemberOrder;
import com.zjl.educenter.entity.UcenterMember;
import com.zjl.educenter.entity.vo.RegisterVo;
import com.zjl.educenter.service.UcenterMemberService;
import org.aspectj.weaver.patterns.IToken;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 会员表 前端控制器
 * </p>
 *
 * @author zjl
 * @since 2023-06-16
 */
@RestController
@RequestMapping("/educenter/member")
@CrossOrigin
public class UcenterMemberController {


    @Autowired
    private UcenterMemberService memberService;

    //登录
    @PostMapping("/login")
    public R loginUser(@RequestBody UcenterMember member) {
        //member对象封装手机号密码

        //返回token值,使用jwt
        String token = memberService.login(member);
        return R.ok().data("token", token);
    }


    //注册
    @PostMapping("/register")
    public R registerUser(@RequestBody RegisterVo registerVo) {

        memberService.register(registerVo);

        return R.ok();
    }

    //根据token获取用户信息
    @GetMapping("getMemberInfo")
    public R getMemberInfo(HttpServletRequest request) {
        //调用jwt工具类的方法,根据request对象获取头信息,返回用户id
        String memberId = JwtUtils.getMemberIdByJwtToken(request);

        //根据用户id获取用户信息
        UcenterMember member = memberService.getById(memberId);

        return R.ok().data("userInfo", member);
    }

    //根据用户id获取用户信息
    @PostMapping("getUserInfoOrder/{id}")
    public UcenterMemberOrder getUserInfoOrder(@PathVariable String id) {

        UcenterMember member = memberService.getById(id);
        UcenterMemberOrder ucenterMemberOrder = new UcenterMemberOrder();
        //把member对象里面的值复制给UcenterMemberOrder对象
        BeanUtils.copyProperties(member, ucenterMemberOrder);

        return ucenterMemberOrder;
    }

}

