package com.li.oauthserver.security;

import com.li.oauthserver.model.Role;
import com.li.oauthserver.model.User;
import com.li.oauthserver.service.IRoleService;
import com.li.oauthserver.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Classname UserServiceDetail
 * @Description TODO
 * @Author 李号东 lihaodongmail@163.com
 * @Date 2019-03-19 16:55
 * @Version 1.0
 */
@Slf4j
@Service
public class UserServiceDetail implements UserDetailsService {

    @Autowired
    private IUserService userService;

    @Autowired
    private IRoleService roleService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userService.findByUserName(username);
        log.info("登录用户：" + username);
        if (user == null) {
            log.info("登录用户：" + username + " 不存在");
            throw new UsernameNotFoundException("登录用户：" + username + " 不存在");
        }
        //获取用户拥有的角色
        List<Role> roles = roleService.findRolesByUserId(user.getId());
        return new SecurityUser(user.getId(),user.getUsername(), user.getPassword(), roles);
    }
}
