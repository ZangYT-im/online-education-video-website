package com.zjl.educenter;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

/**
 * @User: zangj
 * @Author: zjl
 * @Date: 2023/6/16
 * @Time: 9:29
 * @project:college_parent
 */

@ComponentScan({"com.zjl"})
@SpringBootApplication
@MapperScan("com.zjl.educenter.mapper")
public class UcenterApplication {
    public static void main(String[] args) {
        SpringApplication.run(UcenterApplication.class);
        System.out.println("Ucenter启动成功");
    }
}
