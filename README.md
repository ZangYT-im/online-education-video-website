# 在线教育视频网站

#### 介绍

该项目主要用于实践和学习；

- 开发时间：2023.5.17 - 2023.6.28

- 技术栈：

  - 后端：`SpringBoot` + `SpringCloud（Nacos，Feign，GateWay）` + `MyBatis-Plus` + `HttpClient` + `EasyExcel` + `nginx`
  - 前端：`Node.js` + `Vue.js` +`Element-ui` + `NUXT` + `ECharts`
  - 其他及涉及到的中间件：Redis、阿里云OSS、阿里云视频点播

- 项目背景：

  ​		在线教育视频网站，采用B2C模式，使用SpringCloud微服务架构，持久层用MyBatis-Plus，使用Swagger生成接口文档，同时接入了阿里云视频点播、阿里云OSS，业务中使用了ECharts做图表展示，注册分布式单点登录使用了JWT；前后端分离开发，系统分为前台用户系统和后台管理系统两部分。

#### 软件架构

软件架构说明：

- vue-front-1010：前台前端项目
- vue-ui：后台前端项目

功能模块:

1. 前台用户系统：首页数据显示、课程列表和详情、课程支付，课程视频播放，微信登陆，微信支付等；

2. 后台管理系统包括：讲师管理、课程管理、统计分析、Banner管理、订单管理、权限管理等；

