package com.li.oauthserver.config;

import com.li.oauthserver.handler.AuthExceptionEntryPoint;
import com.li.oauthserver.handler.CustomAccessDeniedHandler;
import com.li.oauthserver.handler.CustomTokenEnhancer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import java.util.Arrays;

/**
 * @Classname ResourceServerConfiguration
 * @Description 资源服务器
 * @Author 李号东 lihaodongmail@163.com
 * @Date 2019-03-18 22:51
 * @Version 1.0
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

    private static final String DEMO_RESOURCE_ID = "order";

    @Autowired
    private CustomAccessDeniedHandler accessDeniedHandler;


    @Autowired
    private AuthExceptionEntryPoint authExceptionEntryPoint;

    @Autowired
    CustomTokenEnhancer customTokenEnhancer;

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources
                .resourceId(DEMO_RESOURCE_ID).stateless(true)
                .authenticationEntryPoint(authExceptionEntryPoint) // 外部定义的token错误进入的方法
                .accessDeniedHandler(accessDeniedHandler); // 没有权限的进入方法

        resources.tokenServices(tokenServices());
    }
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()
                .authorizeRequests()
                // 配置order访问控制，必须认证后才可以访问
                .antMatchers("/order/**").authenticated();
    }

    @Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(accessTokenConverter());
    }

    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey("123");
        return converter;
    }

    @Bean
    public DefaultTokenServices tokenServices() {

        DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
        defaultTokenServices.setTokenStore(tokenStore());
        return defaultTokenServices;
    }

}