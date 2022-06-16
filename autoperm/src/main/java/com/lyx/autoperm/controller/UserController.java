package com.lyx.autoperm.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lyx.autoperm.entity.Permission;
import com.lyx.autoperm.entity.Role;
import com.lyx.autoperm.entity.User;
import com.lyx.autoperm.exception.UserCodeEnum;
import com.lyx.autoperm.exception.UserException;
import com.lyx.autoperm.service.IPermissionService;
import com.lyx.autoperm.service.IRoleService;
import com.lyx.autoperm.service.IUserService;
import com.lyx.autoperm.utils.JwtUtils;
import com.lyx.autoperm.utils.R;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import java.util.Set;

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
        return R.ok()
                .put("user",byId)
                .put("roles",roles)
                .put("permissions",permissions);
    }
}
