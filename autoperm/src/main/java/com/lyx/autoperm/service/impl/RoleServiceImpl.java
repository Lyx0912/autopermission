package com.lyx.autoperm.service.impl;

import com.lyx.autoperm.entity.Role;
import com.lyx.autoperm.mapper.RoleMapper;
import com.lyx.autoperm.service.IRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author liyongxuan
 * @since 2022-06-09
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

    @Autowired
    private RoleMapper roleMapper;

    /**
     * 根据用户id查询所有的角色
     * @param id
     * @return void
     * @author 黎勇炫
     * @create 2022/6/13
     * @email 1677685900@qq.com
     */
    @Override
    public Set<String> queryRolesById(String id) {
        return roleMapper.queryRolesByUserId(id);
    }
}
