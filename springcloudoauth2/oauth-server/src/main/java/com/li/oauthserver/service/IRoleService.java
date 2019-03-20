package com.li.oauthserver.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.li.oauthserver.model.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lihaodong
 * @since 2019-03-14
 */
public interface IRoleService extends IService<Role> {

    /**
     * 根据用户id查找该用户角色
     * @param userId
     * @return
     */
    List<Role> findRolesByUserId(@Param("userId") Integer userId);
}
