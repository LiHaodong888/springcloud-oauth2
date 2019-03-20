package com.li.oauthserver.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AbstractAuthenticationFailureEvent;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

/**
 * @Classname AuthenticationFailureEvenHandler
 * @Description 登录失败映射类
 * @Author 李号东 lihaodongmail@163.com
 * @Date 2019-03-20 10:42
 * @Version 1.0
 */
@Slf4j
@Component
public class AuthenticationFailureEvenHandler implements ApplicationListener<AbstractAuthenticationFailureEvent> {
    @Override
    public void onApplicationEvent(AbstractAuthenticationFailureEvent event) {
        // 登录的authentication 对象
        AuthenticationException authenticationException = event.getException();
        // 登录的authenticationException 对象
        Authentication authentication = (Authentication) event.getSource();

        log.info("用户：{} 登录失败，异常：{}", authentication.getPrincipal(), authenticationException.getLocalizedMessage());


    }
}
