package com.zjl.staservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @User: zangj
 * @Author: zjl
 * @Date: 2023/6/26
 * @Time: 10:01
 * @project:college_parent
 */

@SpringBootApplication
@ComponentScan(basePackages = {"com.zjl"})
@EnableDiscoveryClient
@EnableFeignClients
@MapperScan("com.zjl.staservice.mapper")
@EnableScheduling//开启定时任务
public class StaApplication {
    public static void main(String[] args) {
        SpringApplication.run(StaApplication.class,args);
        System.out.println("sta启动成功!");
    }
}
