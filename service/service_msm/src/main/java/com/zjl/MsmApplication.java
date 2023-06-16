package com.zjl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.stereotype.Component;

/**
 * @User: zangj
 * @Author: zjl
 * @Date: 2023/6/15
 * @Time: 16:49
 * @project:college_parent
 */

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@Component("com.zjl")
public class MsmApplication {
    public static void main(String[] args) {
        SpringApplication.run(MsmApplication.class,args);
        System.out.println("msm启动成功");
    }
}
