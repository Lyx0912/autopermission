package com.lyx.autoperm.service;

import com.lyx.autoperm.entity.Role;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Set;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author liyongxuan
 * @since 2022-06-09
 */
public interface IRoleService extends IService<Role> {

    /**
     * 根据用户id查询所有的角色
     * @param id
     * @return void
     * @author 黎勇炫
     * @create 2022/6/13
     * @email 1677685900@qq.com
     */
    Set<String> queryRolesById(String id);
}
