package com.lyx.autoperm.controller;


import com.lyx.autoperm.entity.UserRole;
import com.lyx.autoperm.service.IUserRoleService;
import com.lyx.autoperm.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author liyongxuan
 * @since 2022-06-09
 */
@RestController
@RequestMapping("/autoperm/userrole")
public class UserRoleController {

    @Autowired
    private IUserRoleService userRoleService;

    /**
     * 添加用户角色
     * @param uid 用户id
     * @param rid 角色id
     * @return com.lyx.autoperm.utils.R
     * @author 黎勇炫
     * @create 2022/6/23
     * @email 1677685900@qq.com
     */
    @PutMapping("/add")
    public R add(@RequestParam Integer uid,@RequestParam Integer rid){
        UserRole userRole = new UserRole();
        // 设置用户编号
        userRole.setUserId(uid);
        // 设置角色编号
        userRole.setRoleId(rid);
        userRoleService.save(userRole);
        return R.ok();
    }
}
