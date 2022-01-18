## 一、简介
 实现新用户注册(发送邮件通知)以及修改,锁定,删除的demo功能演示

## 二、代码介绍
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
    

## 三、安装部署流程
(docker环境部署, 代码、jar包保存路径/home/apps)

#### 1. (获取并)运行maven-docker镜像进行代码拉取和打包jar
* 启动maven容器
* 进入容器(映射宿主机的文件夹/home/apps)
* git拉取代码
* mvn命令打包项目
* 退出
    
#### 2. (获取并)运行nacos-docker镜像作为注册中心
* 拉取并启动nacos容器
* 命名为test-nacos(后面会用到)
* 开放端口8848

#### 3. (获取和)构建自己的mysql-docker镜像并运行
* 构建自己的mysql镜像(主要是导入数据库初始化脚本)
* 启动自己的数据库容器,命名为test-mysql(后面会用到)
* 开放端口3306和33060

#### 4. (基于java8)构建自己的apps-docker镜像并运行
* 准备所需的jar包(4个:gateway,user,mail,webui)和启动脚本(startup.sh)
* 基于java8构建自己的apps镜像
* 启动apps容器
* 开放访问端口8000

#### 5. 浏览器访问前端页面测试功能
* http://[docker_host_ip]:8000/

## 四、安装说明
```shell
# 获取安装脚本
wget -P /tmp https://raw.githubusercontent.com/wumugulu/dev-test/main/install.sh

# 添加执行权限 
sudo chmod +x /tmp/install.sh

# 执行安装脚本
sh /tmp/install.sh
```

