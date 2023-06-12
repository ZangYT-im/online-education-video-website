package com.zjl.eduservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

/**
 * @User: zangj
 * @Author: zjl
 * @Date: 2023/5/18
 * @Time: 9:13
 * @project:college_parent
 */

@MapperScan("com.zjl.mappper")
@SpringBootApplication
@ComponentScan(basePackages = {"com.zjl"})
public class EduApplication {
    public static void main(String[] args) {
        SpringApplication.run(EduApplication.class,args);
        System.out.println("项目启动成功！");
    }
}
