package com.lyx.autoperm.controller;


import com.lyx.autoperm.entity.Permission;
import com.lyx.autoperm.entity.vo.MenuVO;
import com.lyx.autoperm.exception.UserCodeEnum;
import com.lyx.autoperm.exception.UserException;
import com.lyx.autoperm.service.IPermissionService;
import com.lyx.autoperm.utils.JwtUtils;
import com.lyx.autoperm.utils.R;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
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
@RequestMapping("/autoperm/permisson")
public class PermissonController {

    @Autowired
    private IPermissionService permissionService;

    /**
     * 构建权限菜单树
     * @param req
     * @return com.lyx.autoperm.utils.R
     * @author 黎勇炫
     * @create 2022/6/21
     * @email 1677685900@qq.com
     */
    @GetMapping("/createRouter")
    public R getRouter(HttpServletRequest req){
        // 获取用户id
        String id = JwtUtils.getMemberIdByJwtToken(req);
        if(StringUtils.isEmpty(id)){
            throw new UserException(UserCodeEnum.TOKEN_NOT_FOUND);
        }
        // 根据角色查询所有的菜单
        List<Permission> permissions = permissionService.queryPermissionsByRoles(id);
        // 构建菜单树
        List<MenuVO> menus = permissionService.buildMenus(permissions);

        return R.ok().setData(menus);
    }

    /**
     * 权限列表
     * @return
     * @author 黎勇炫
     * @create 2022/6/24
     * @email 1677685900@qq.com
     */
    @GetMapping("/list")
    public R list(){
        return R.ok().setData(permissionService.queryPermissionsByRoles(null));
    }

    /**
     * 添加权限
     * @return com.lyx.autoperm.utils.R
     * @author 黎勇炫
     * @create 2022/6/26
     * @email 1677685900@qq.com
     */
    @PutMapping("/add")
    public R add(@RequestBody Permission permission){
        permissionService.save(permission);
        return R.ok();
    }

    /**
     * 根据菜单id删除菜单
     * @param ids
     * @return com.lyx.autoperm.utils.R
     * @author 黎勇炫
     * @create 2022/6/26
     * @email 1677685900@qq.com
     */
    @DeleteMapping("/remove/{ids}")
    public R remove(@PathVariable String[] ids){
        permissionService.removeBatchByIds(Arrays.asList(ids));
        return R.ok();
    }

    /**
     * 根据id查询菜单信息
     * @param id 菜单id
     * @return com.lyx.autoperm.utils.R
     * @author 黎勇炫
     * @create 2022/6/26
     * @email 1677685900@qq.com
     */
    @GetMapping("/info/{id}")
    public R info(@PathVariable Integer id){
        Permission byId = permissionService.getById(id);
        return R.ok().setData(byId);
    }

    /**
     * 更新菜单
     * @param permission
     * @return com.lyx.autoperm.utils.R
     * @author 黎勇炫
     * @create 2022/6/26
     * @email 1677685900@qq.com
     */
    @PutMapping("/update")
    public R update(@RequestBody Permission permission){
        permissionService.updateById(permission);
        return R.ok();
    }
}
