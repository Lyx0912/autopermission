package com.lyx.autoperm.service;

import com.lyx.autoperm.entity.Role;
import com.lyx.autoperm.entity.UserRole;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author liyongxuan
 * @since 2022-06-09
 */
public interface IUserRoleService extends IService<UserRole> {

    /**
     * 根据用户id查询用户的角色列表
     * @param id
     * @return java.util.List<com.lyx.autoperm.entity.Role>
     * @author 黎勇炫
     * @create 2022/6/22
     * @email 1677685900@qq.com
     */
    List<Role> queryRolesByUid(String id);
}
