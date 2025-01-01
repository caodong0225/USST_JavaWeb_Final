# 说明

项目架构：Spring+Spring MVC+Mybatis（传统SSM架构），模板引擎采用thymeleaf，数据库使用mysql8

### 部署教程

首先找到`src\main\resources\db`路径下面的sql建表语句，创建数据库spm，完成数据库的创建。本项目使用的数据库为mysql8。

如果需要设置超级管理员用户，请找到数据库里面的`user_role`表，完成权限的创建（更改完成后记得重新启动一遍tomcat容器以保证缓存的刷新）支持的角色名称有admin（管理员），advertisers（广告主）两类。

打开resources/config.properties里面，记得将你文件上传的路径配置为你本地的真实磁盘的路径

### 使用教程

此处是广告投放的管理界面系统

输入：http://localhost:8080/auth/login进入广告投放的登录界面。


### 接口

/ad/{fileName}  广告页面
/ad/images/{fileName}   获取广告封面图片
