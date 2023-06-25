package com.zjl.eduorder;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

import javax.swing.*;

/**
 * @User: zangj
 * @Author: zjl
 * @Date: 2023/6/25
 * @Time: 8:46
 * @project:college_parent
 */

@SpringBootApplication
@ComponentScan(basePackages = {"com.zjl"})
@MapperScan("com.zjl.eduorder.mapper")
@EnableDiscoveryClient
@EnableFeignClients
public class OrdersApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrdersApplication.class,args);

        System.out.println("orders启动成功");
    }
}
