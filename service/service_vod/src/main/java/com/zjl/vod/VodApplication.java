package com.zjl.vod;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * @User: zangj
 * @Author: zjl
 * @Date: 2023/6/12
 * @Time: 9:47
 * @project:college_parent
 */



@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)//不去加载数据库，不然会报错
@ComponentScan(basePackages = {"com.zjl"})
public class VodApplication {
    public static void main(String[] args) {
        SpringApplication.run(VodApplication.class,args);
    }

}
