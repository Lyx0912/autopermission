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
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.LinkedList;
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
    public List<MenuVO> buildMenus(Set<Permission> permissions) {
        List<MenuVO> menus = new LinkedList<MenuVO>();
        // 遍历权限列表，构建菜单
        for (Permission item : permissions) {
            MenuVO menu = new MenuVO();
            menu.setHidden(false);
            menu.setPath(item.getPath());
            menu.setComponent(buildComponent(item));item.getType().toString().equals(MenuType.MENU.getCode().toString());
            menu.setMeta(new MetaVO(item.getPermName(), item.getIcon()));
            Set<Permission> cMenus = item.getChildren();
            // 如果有子菜单
            if (!CollectionUtils.isEmpty(cMenus) && cMenus.size() > 0 )
            {
                menu.setAlwaysShow(true);
                menu.setRedirect("noRedirect");
                menu.setChildren(buildMenus(cMenus));
            }
            else if (item.getType().toString().equals(MenuType.MENU.getCode().toString()) && item.getParentId().equals(0))
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

}
