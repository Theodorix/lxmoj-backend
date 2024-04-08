### 酷客 CucKer 编程代码测评系统

本项目是一个基于 Vue.js 和 Spring Boot 构建的在线编程学习和评测平台。用户在此平台浏览和作答题目，提交题目作答内容后等待管理员审核给出判题结果。

1. 用户体验：通过简易的注册和登录流程，用户可轻松访问平台，并选择感兴趣的编程题目进行练习。
2. 题目管理和判题：管理员具备发布、修改、删除题目的权限，对用户提交的题目作答情况进行人工判题。

### 项目介绍

基于 Vue3+Spring Boot 构建的前后端分离在线编程学习和评测平台，采用单体应用服务架构。

后端项目地址：https://github.com/Theodorix/lxmoj-backend

前端项目地址：https://github.com/Theodorix/lxmoj-frontend

### 启动项目

#### 配置数据库

1. 首先，确保已经安装了 MySQL 8.0.28 版本的数据库。
2. 打开项目中的 `application.yml` 文件。
3. 在配置文件中，根据你的数据库配置信息，修改以下属性：
   
   ```yaml
   datasource:
       driver-class-name: com.mysql.cj.jdbc.Driver
       url: jdbc:mysql://localhost:3310/your_database_name
       username: your_database_username
       password: your_database_password
   ```
请将 your_database_name、your_database_username 和 your_database_password 替换为实际的数据库名称、用户名和密码。

#### 配置 Maven

1. 确保已经安装了 Maven，并且配置了环境变量。
2. 在命令行中进入项目根目录。
3. 执行以下命令安装项目所需的依赖：
   
    ```bash
   mvn clean install
    ```

#### 开启 Redis

1. 确保已经安装了 Redis，并且启动了 Redis 服务。
2. 如果 Redis 使用了非默认配置（例如非默认端口），请确保在项目的配置文件中进行相应的修改。
    
   ```yaml
   redis:
       database: 1
       host: localhost
       port: 6379
       timeout: 5000
   ```

运行 MainApplication 启动项目




