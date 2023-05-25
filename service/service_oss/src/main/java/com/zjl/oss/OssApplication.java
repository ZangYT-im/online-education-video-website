package com.zjl.oss;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * @User: zangj
 * @Author: zjl
 * @Date: 2023/5/25
 * @Time: 23:47
 * @project:college_parent
 */


@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)//不去自动加载数据库，否则会去加载数据库，而产生错误
@ComponentScan(basePackages = {"com.zjl"})
public class OssApplication {
    public static void main(String[] args) {
        SpringApplication.run(OssApplication.class,args);
    }
}
