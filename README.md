# 基于Spring Cloud Oauth2 JWT搭建微服务的安全认证中心
使用OAuth2实现多个微服务的统一认证授权,通过向OAUTH服务发送某个类型的grant type进行集中认证和授权获得access token,这个access token是受其他微服务信任的。后续访问中可以通过这个access token来进行
eureka-server: 服务注册和发现
oauth-server: 安全认证包括资源服务器和认证服务器

