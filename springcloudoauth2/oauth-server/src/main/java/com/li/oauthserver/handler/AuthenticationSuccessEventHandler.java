package com.li.oauthserver.handler;

import cn.hutool.core.collection.CollUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

/**
 * @Classname AuthenticationSuccessEventHandler
 * @Description 登录成功映射类
 * @Author 李号东 lihaodongmail@163.com
 * @Date 2019-03-20 11:01
 * @Version 1.0
 */
@Slf4j
@Component
public class AuthenticationSuccessEventHandler implements ApplicationListener<AuthenticationSuccessEvent> {

    @Override
    public void onApplicationEvent(AuthenticationSuccessEvent event) {

        Authentication authentication = (Authentication) event.getSource();
        if (CollUtil.isNotEmpty(authentication.getAuthorities())) {
            log.info("用户：{} 登录成功", authentication.getPrincipal());
        }

    }
}
