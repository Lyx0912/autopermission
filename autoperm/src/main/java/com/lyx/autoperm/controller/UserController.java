package com.lyx.autoperm.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lyx.autoperm.constant.PageConstant;
import com.lyx.autoperm.entity.Permission;
import com.lyx.autoperm.entity.Role;
import com.lyx.autoperm.entity.User;
import com.lyx.autoperm.entity.UserRole;
import com.lyx.autoperm.entity.vo.MenuVO;
import com.lyx.autoperm.exception.UserCodeEnum;
import com.lyx.autoperm.exception.UserException;
import com.lyx.autoperm.service.IPermissionService;
import com.lyx.autoperm.service.IRoleService;
import com.lyx.autoperm.service.IUserRoleService;
import com.lyx.autoperm.service.IUserService;
import com.lyx.autoperm.utils.JwtUtils;
import com.lyx.autoperm.utils.PageUtils;
import com.lyx.autoperm.utils.R;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author liyongxuan
 * @since 2022-06-09
 */
@RestController
@RequestMapping("/autoperm/user")
public class UserController {

    @Autowired
    private IUserService userService;
    @Autowired
    private IRoleService roleService;
    @Autowired
    private IUserRoleService userRoleService;
    @Autowired
    private IPermissionService permissionService;


    @GetMapping("/info")
    public R getUserInfo(HttpServletRequest req){
        // 获取用户id
        String id = JwtUtils.getMemberIdByJwtToken(req);
        if(StringUtils.isEmpty(id)){
            throw new UserException(UserCodeEnum.TOKEN_NOT_FOUND);
        }
        // 根据用户id获取用户信息
        User byId = userService.getById(id);
        if(byId == null){
            throw new UserException(UserCodeEnum.USER_NOT_FOUNT);
        }
        // 根据用户id获取用户的角色列表
        Set<String> roles = roleService.queryRolesById(id);
        // 根据角色查询所有的菜单
        Set<Permission> permissions = permissionService.queryPermissionsByRoles(id);
        // 构建菜单树
        List<MenuVO> menus = permissionService.buildMenus(permissions);
        return R.ok()
                .put("user",byId)
                .put("roles",roles)
                .put("permissions",permissions);
    }

    /**
     * 分页查询用户
     * @return com.lyx.autoperm.utils.R
     * @author 黎勇炫
     * @create 2022/6/21
     * @email 1677685900@qq.com
     */
    @GetMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        // 构建分页对象
        Page<User> page = PageUtils.buildPage(params);
        // 分页查询用户
        Page<User> res = userService.page(page);
        // 查找对应的角色
        List<User> records = res.getRecords().stream().map(item -> {
            item.setRoles(roleService.queryRolesById(item.getId()));
            return item;
        }).collect(Collectors.toList());

        res.setRecords(records);
        return R.ok().setData(res);
    }

    @DeleteMapping("/removeRole")
    public R removeRole(@RequestParam String id,@RequestParam String role){
        // 获取角色id
        Integer roleId = roleService.getOne(new QueryWrapper<Role>().eq("role_name",role)).getId();
        // 从关联表中删除对应的角色
        userRoleService.remove(new QueryWrapper<UserRole>().eq("role_id",roleId).eq("user_id",id));
        return R.ok();
    }

    /**
     * 根据id删除指定的用户
     * @param ids
     * @return com.lyx.autoperm.utils.R
     * @author 黎勇炫
     * @create 2022/6/22
     * @email 1677685900@qq.com
     */
    @DeleteMapping("/remove")
    public R remove(String[] ids){
        userRoleService.removeBatchByIds(Arrays.asList(ids));
        return R.ok();
    }

}
