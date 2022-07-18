package com.lyx.autoperm.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lyx.autoperm.constant.ComponentConstant;
import com.lyx.autoperm.constant.MenuType;
import com.lyx.autoperm.entity.Permission;
import com.lyx.autoperm.entity.vo.MenuVO;
import com.lyx.autoperm.entity.vo.MetaVO;
import com.lyx.autoperm.mapper.PermissionMapper;
import com.lyx.autoperm.service.IPermissionService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author liyongxuan
 * @since 2022-06-09
 */
@Service("permissionServiceImpl")
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements IPermissionService {


    @Autowired
    private PermissionMapper permissionMapper;
    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 根据用户查询所有的权限菜单详情
     * @param id
     * @return java.util.Set<com.lyx.autoperm.entity.Permission>
     * @author 黎勇炫
     * @create 2022/6/13
     * @email 1677685900@qq.com
     */
    @Override
    public List<Permission> queryPermissionsByRoles(String id) {
        // 查询权限列表
        List<Permission> permissions = queryPermissions(id);
        // 遍历权限列表，筛选出一级权限
        List<Permission> perm = permissions.stream().filter(item -> {
            return item.getParentId() == 0;
        }).map(l1 -> {
            l1.setChildren(findChildren(permissions, l1));
            return l1;
        }).sorted((Comparator.comparingInt(o -> (o.getSort() == null ? 0 : o.getSort())))).collect(Collectors.toList());

        return perm;
    }

    private List<Permission> queryPermissions(String id) {
        if(StringUtils.isEmpty(id)){
            return permissionMapper.selectList(null);
        }else {
            return permissionMapper.queryPermissionsDetail(id);
        }
    }

    /**
     *
     * @param permissions 权限列表
     * @param l1 父权限
     * @return java.util.List<com.lyx.autoperm.entity.Permission>
     * @author 黎勇炫
     * @create 2022/6/25
     * @email 1677685900@qq.com
     */
    private List<Permission> findChildren(List<Permission> permissions, Permission l1) {

        List<Permission> children = permissions.stream().filter(perm -> {
            return perm.getParentId().toString().equals(l1.getId().toString());
        }).sorted((Comparator.comparingInt(o -> (o.getSort() == null ? 0 : o.getSort())))).collect(Collectors.toList());

        return children;
    }

    /**
     * 构建前端菜单树
     *
     * @param permissions
     * @return java.util.List<com.lyx.autoperm.entity.vo.MenuVO>
     * @author 黎勇炫
     * @create 2022/6/20
     * @email 1677685900@qq.com
     */
    @Override
    public List<MenuVO> buildMenus(List<Permission> permissions) {
        List<MenuVO> menus = new LinkedList<MenuVO>();
        // 遍历权限列表，构建菜单
        for (Permission item : permissions) {
            MenuVO menu = new MenuVO();
            menu.setHidden(false);
            menu.setPath(item.getPath());
            menu.setComponent(buildComponent(item));item.getType().toString().equals(MenuType.MENU.getCode().toString());
            menu.setMeta(new MetaVO(item.getPermName(), item.getIcon()));
            List<Permission> cMenus = item.getChildren();
            // 如果有子菜单
            if (!CollectionUtils.isEmpty(cMenus) && cMenus.size() > 0 )
            {
                menu.setAlwaysShow(true);
                menu.setRedirect("noRedirect");
                menu.setChildren(buildMenus(cMenus));
            }
            else if (item.getParentId().equals(0))
            {
                menu.setMeta(null);
                List<MenuVO> childrenList = new ArrayList<MenuVO>();
                MenuVO children = new MenuVO();
                children.setPath(menu.getPath());
                children.setComponent(menu.getComponent());
                menu.setComponent(ComponentConstant.LAYOUT);
                children.setName(StringUtils.capitalize(menu.getPath()));
                children.setMeta(new MetaVO(item.getPermName(), item.getIcon()));
                childrenList.add(children);
                menu.setChildren(childrenList);
            }
            menus.add(menu);
        }

        return menus;
    }

    /**
     * 根据会员编号查询
     *
     * @param id 角色编号
     * @return java.util.List<java.lang.String>
     * @author 黎勇炫
     * @create 2022/6/28
     * @email 1677685900@qq.com
     */
    @Override
    public List<String> getPermNameByRole(Integer id) {
        return permissionMapper.getPermNameByRole(id);
    }


    /**
     * 构建前端组件component
     * @param permission 权限对象
     * @return java.lang.String
     * @author 黎勇炫
     * @create 2022/6/20
     * @email 1677685900@qq.com
     */
    public String buildComponent(Permission permission){
        String component = ComponentConstant.LAYOUT;
        if(!StringUtils.isEmpty(permission.getComponent())){
            component =  permission.getComponent();
        }
        return component;
    }

    /**
     * 核对当前用户是否具有某一权限
     * @param permission 权限标识
     * @return boolean
     * @author 黎勇炫
     * @create 2022/6/28
     * @email 1677685900@qq.com
     */
    public boolean hasPerms(String permission){
        // 如果权限标识为空返回false
        if(StringUtils.isEmpty(permission)){
            return false;
        }
        // 从redis中获取当前登录用户的权限列表
        Set<String> perms = (Set<String>) redisTemplate.opsForValue().get(SecurityContextHolder.getContext().getAuthentication().getName());
        // 权限列表为空返回null
        if(CollectionUtils.isEmpty(perms)){
            return false;
        }
        return perms.contains(permission);
    }

}
