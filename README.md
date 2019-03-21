# 基于Spring Cloud Oauth2 JWT搭建微服务的安全认证中心
使用OAuth2实现多个微服务的统一认证授权,通过向OAUTH服务发送某个类型的grant type进行集中认证和授权获得access token,这个access token是受其他微服务信任的。后续访问中可以通过这个access token来进行
## 系统结构
eureka-server: 服务注册和发现  
oauth-server: 安全认证包括资源服务器和认证服务器
## 技术栈
1. Spring Cloud
1. Mybatis-plus
2. Oauth2
## 运行
运行eureka-server 端口号8761
运行oauth-server 端口号8080
## 测试
启动springboot应用之后，使用http工具通过接口 /registry 进行注册
![用户注册](https://static.lhdyx.cn/images/13d182813b53432ca7bdb48a50239657.png)
注册成功后 使用账号密码去登录

password模式：
http://localhost:8080/oauth/token? username=user&password=111111& grant_type=password&scope=select& client_id=client_2&client_secret=123456
![password登录](https://static.lhdyx.cn/images/969365f791e5434f8ec6f51e2d661cd8.png)

看到成功返回token

在配置中，我们已经配置了对order资源的保护，如果直接访问： http://localhost:8080/order/1 ，会得到这样的响应
![order资源获取](https://static.lhdyx.cn/images/25c477df5c6d4ec8bab07f92928cee8d.png)

显示没有权限去访问

而对于未受保护的product资源 得到返回的结果
![受保护的product资源](https://static.lhdyx.cn/images/c26bce9d8b4f41ebba7c39a069cbefbc.png)

携带accessToken参数访问受保护的资源, 使用password模式获得的token 

得到了之前匿名访问无法获取的资源
![匿名访问获取的资源](https://static.lhdyx.cn/images/3fdcbdcc18de40b084113ff476dd8a7f.png)


client模式

client模式：

http://localhost:8080/oauth/token?grant_type=client_credentials&scope=select&client_id=client_1&client_secret=123456

响应如下：
![client登录](https://static.lhdyx.cn/images/184cc638695b4d9f80e2e02b1834812a.png)

使用client模式获得的token:
去访问 http://localhost:8080/order/1?access_token=xxxx
![client模式token获取资源](https://static.lhdyx.cn/images/8d010286c45b444896894c54fe2ec00d.png)

