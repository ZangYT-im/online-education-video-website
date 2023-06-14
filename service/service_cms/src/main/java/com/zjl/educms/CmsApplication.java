package com.zjl.educms;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @User: zangj
 * @Author: zjl
 * @Date: 2023/5/18
 * @Time: 9:13
 * @project:college_parent
 */

//@EnableFeignClients
//@EnableDiscoveryClient//进行nacos注册的注解
@MapperScan("com.zjl.educms.mappper")
@SpringBootApplication
@ComponentScan(basePackages = {"com.zjl"})
public class CmsApplication {
    public static void main(String[] args) {
        SpringApplication.run(CmsApplication.class,args);
        System.out.println("Cms项目启动成功！");
    }
}
