package com.li.oauthserver.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.li.oauthserver.model.User;

/**
 * <p>
 *  用户服务类
 * </p>
 *
 * @author lihaodong
 * @since 2019-03-14
 */
public interface IUserService extends IService<User> {


    /**
     * 通过用户名查找用户
     *
     * @param username 用户名
     * @return 用户信息
     */
    User findByUserName(String username);

    User create(String username, String password);

}
