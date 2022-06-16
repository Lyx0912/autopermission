package com.lyx.autoperm.service;

import com.lyx.autoperm.entity.Permission;
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
public interface IPermissionService extends IService<Permission> {

    /**
     * 根据角色列表查询所有的权限菜单
     * @param id 用户编号
     * @return java.util.Set<com.lyx.autoperm.entity.Permission>
     * @author 黎勇炫
     * @create 2022/6/13
     * @email 1677685900@qq.com
     */
    Set<Permission> queryPermissionsByRoles(String id);
}
