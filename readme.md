## 简介
 实现新用户注册(发送邮件通知)以及修改,锁定,删除的demo功能演示

## 代码相关
  * springcloud, springboot, nacos, mysql, mybatis, mybatis-plus
  * 应用共分为4个微服务模块(和若干公用模块),分别为
    - gateway -- 网关模块
    - service-user -- 用户模块
    - service-mail -- 邮件模块
    - webui -- 前端展示模块(vue+elementui,纯静态页面,使用springboot承载,前后端分离)
    
#### 代码结构如下:
```shell
dev-test.
├─file
│  └─shell
│      ├─apps
│      └─mysql
├─TestGateway
│  └─src
│     ├─main
│     │  ├─java
│     │  │  └─com
│     │  │      └─wumugulu
│     │  │          └─devtest
│     │  │              └─gateway
│     │  └─resources
│     └─test
│        └─java
│
├─TestPublic
│  ├─TestBaseData
│  │  └─src
│  │     ├─main
│  │     │  ├─java
│  │     │  │  └─com
│  │     │  │      └─wumugulu
│  │     │  │          └─devtest
│  │     │  │              └─basedata
│  │     │  └─resources
│  │     └─test
│  │        └─java
│  │
│  ├─TestCommon
│  │  └─src
│  │     ├─main
│  │     │  ├─java
│  │     │  │  └─com
│  │     │  │      └─wumugulu
│  │     │  │          └─devtest
│  │     │  │              └─common
│  │     │  │                  ├─beans
│  │     │  │                  ├─const
│  │     │  │                  └─utils
│  │     │  └─resources
│  │     └─test
│  │         └─java
│  │
│  └─TestConfig
│      └─src
│         ├─main
│         │  ├─java
│         │  └─resources
│         └─test
│             └─java
│
├─TestService
│  ├─TestMail
│  │  └─src
│  │     └─main
│  │        ├─java
│  │        │  └─com
│  │        │      └─wumugulu
│  │        │          └─devtest
│  │        │              └─service
│  │        │                  └─mail
│  │        │                      ├─config
│  │        │                      ├─controller
│  │        │                      ├─mapper
│  │        │                      └─service
│  │        │                          └─impl
│  │        └─resources
│  │            └─mapper
│  │
│  └─TestUser
│      └─src
│         ├─main
│         │  ├─java
│         │  │  └─com
│         │  │      └─wumugulu
│         │  │          └─devtest
│         │  │              └─service
│         │  │                  └─user
│         │  │                      ├─config
│         │  │                      ├─controller
│         │  │                      ├─mapper
│         │  │                      └─service
│         │  │                          ├─feign
│         │  │                          └─impl
│         │  └─resources
│         │      └─mapper
│         └─test
│             └─java
│
└─TestWeb
    └─src
       └─main
          ├─java
          │  └─com
          │      └─wumugulu
          │          └─devtest
          │              └─webui
          └─resources
              └─static
                  ├─component
                  ├─css
                  │  └─fonts
                  ├─error
                  ├─js
                  └─view

```
    

## 安装运行说明(docker环境部署, 代码保存路径/home/apps)
#### 1. (获取并)运行maven-docker镜像进行代码拉取和打包jar
    
#### 2. (获取并)运行nacos-docker镜像作为注册中心
#### 3. (获取和)构建自己的mysql-docker镜像并运行
#### 4. (基于java8)构建自己的apps-docker镜像并运行
#### 5. 浏览器访问前端页面测试功能



