package com.lyx.autoperm.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lyx.autoperm.entity.Permission;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Set;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author liyongxuan
 * @since 2022-06-09
 */
@Mapper
public interface PermissionMapper extends BaseMapper<Permission> {

    /**
     * @param id 用户id
     * @return java.util.Set<java.lang.String>
     * @author 黎勇炫
     * @create 2022/6/10
     * @email 1677685900@qq.com
     */
    Set<String> queryPermissions(String id);

    /**
     * 根据角色列表查询所有的权限
     * @param id 用户id
     * @return java.util.Set<com.lyx.autoperm.entity.Permission>
     * @author 黎勇炫
     * @create 2022/6/13
     * @email 1677685900@qq.com
     */
    List<Permission> queryPermissionsDetail(String id);

    /**
     * 根据角色查询权限
     * @param id 角色编号
     * @return java.util.List<java.lang.String>
     * @author 黎勇炫
     * @create 2022/6/28
     * @email 1677685900@qq.com
     */
    List<String> getPermNameByRole(Integer id);
}
