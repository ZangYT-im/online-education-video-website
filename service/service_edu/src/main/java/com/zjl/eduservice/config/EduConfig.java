package com.zjl.eduservice.config;

import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.extension.injector.LogicSqlInjector;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @User: zangj
 * @Author: zjl
 * @Date: 2023/5/18
 * @Time: 9:15
 * @project:college_parent
 */

@Configuration
@MapperScan("com.zjl.eduservice.mapper")
public class EduConfig {

    /*
    * 逻辑删除插件
    * */
    @Bean
    public ISqlInjector sqlInjector() {
        return new LogicSqlInjector();
    }

}
