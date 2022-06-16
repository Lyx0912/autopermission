package com.lyx.autoperm.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lyx.autoperm.entity.Permission;
import com.lyx.autoperm.mapper.PermissionMapper;
import com.lyx.autoperm.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author liyongxuan
 * @since 2022-06-09
 */
@Service
public class PermissonServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements IPermissionService {

    @Autowired
    private PermissionMapper permissionMapper;

    /**
     * 根据用户查询所有的权限菜单详情
     *
     * @param id
     * @return java.util.Set<com.lyx.autoperm.entity.Permission>
     * @author 黎勇炫
     * @create 2022/6/13
     * @email 1677685900@qq.com
     */
    @Override
    public Set<Permission> queryPermissionsByRoles(String id) {
        // 查询权限列表
        Set<Permission> permissions = permissionMapper.queryPermissionsDetail(id);
        // 遍历权限列表，筛选出一级权限
        Set<Permission> perm = permissions.stream().filter(item -> {
            return item.getParentId() == 0;
        }).map(l1 -> {
            l1.setChildren(findChildren(permissions, l1));
            return l1;
        }).sorted(((o1, o2) -> {
            return (o1.getSort() == null ? 0 : o1.getSort()) - (o2.getSort() == null ? 0 : o2.getSort());
        })).collect(Collectors.toSet());

        return perm;
    }

    private Set<Permission> findChildren(Set<Permission> permissions, Permission l1) {

        Set<Permission> children = permissions.stream().filter(perm -> {
            return perm.getParentId().toString().equals(l1.getId().toString());
        }).collect(Collectors.toSet());

        return children;
    }
}
