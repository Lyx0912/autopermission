package com.lyx.autoperm.controller;


import com.lyx.autoperm.service.IRoleService;
import com.lyx.autoperm.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
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
@RequestMapping("/autoperm/role")
public class RoleController {

    @Autowired
    private IRoleService roleService;

    @GetMapping("/allRoles")
    public R allRole(){
        return R.ok().setData(roleService.list(null));
    }

}
