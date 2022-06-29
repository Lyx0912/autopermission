package com.lyx.autoperm.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lyx.autoperm.entity.Permission;
import com.lyx.autoperm.entity.Role;
import com.lyx.autoperm.entity.User;
import com.lyx.autoperm.service.IPermissionService;
import com.lyx.autoperm.service.IRoleService;
import com.lyx.autoperm.utils.PageUtils;
import com.lyx.autoperm.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.util.Arrays;
import java.util.Map;
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
@RequestMapping("/autoperm/role")
public class RoleController {

    @Autowired
    private IRoleService roleService;
    @Autowired
    private IPermissionService permissionService;

    /**
     * 角色列表
     * @return com.lyx.autoperm.utils.R
     * @author 黎勇炫
     * @create 2022/6/28
     * @email 1677685900@qq.com
     */
    @PreAuthorize("@permissionServiceImpl.hasPerms('sss')")
    @GetMapping("/allRoles")
    public R allRole(){
        return R.ok().setData(roleService.list(null));
    }

    /**
     * 分页查询角色列表
     * @param params
     * @return com.lyx.autoperm.utils.R
     * @author 黎勇炫
     * @create 2022/6/28
     * @email 1677685900@qq.com
     */
    @PreAuthorize("@permissionServiceImpl.hasPerms('user:manager')")
    @GetMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        // 构建分页对象
        Page<Role> page = PageUtils.buildPage(params);
        // 分页查询用户
        Page<Role> res = roleService.page(page);
        res.setRecords(res.getRecords().stream().map(item->{
            item.setMenuList(permissionService.getPermNameByRole(item.getId()));
            return item;
        }).collect(Collectors.toList()));
        return R.ok().setData(res);
    }

    /**
     * 根据id删除角色
     * @param rid
     * @return com.lyx.autoperm.utils.R
     * @author 黎勇炫
     * @create 2022/6/28
     * @email 1677685900@qq.com
     */
    @DeleteMapping("/remove/{rid}")
    public R remover(@PathVariable("rid") String[] ids){
        roleService.removeBatchByIds(Arrays.asList(ids));
        return R.ok();
    }
}
