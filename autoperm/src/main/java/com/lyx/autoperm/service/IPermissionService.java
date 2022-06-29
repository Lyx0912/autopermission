package com.lyx.autoperm.service;

import com.lyx.autoperm.entity.Permission;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lyx.autoperm.entity.vo.MenuVO;

import java.util.List;
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
    List<Permission> queryPermissionsByRoles(String id);

    /**
     * 构建前端菜单树
     * @param permissions
     * @return java.util.List<com.lyx.autoperm.entity.vo.MenuVO>
     * @author 黎勇炫
     * @create 2022/6/20
     * @email 1677685900@qq.com
     */
    List<MenuVO> buildMenus(List<Permission> permissions);

    /**
     * 根据会员编号查询
     * @param id 角色编号
     * @return java.util.List<java.lang.String>
     * @author 黎勇炫
     * @create 2022/6/28
     * @email 1677685900@qq.com
     */
    List<String> getPermNameByRole(Integer id);
}
