package com.lyx.autoperm.service.impl;

import com.lyx.autoperm.entity.Role;
import com.lyx.autoperm.entity.UserRole;
import com.lyx.autoperm.mapper.UserRoleMapper;
import com.lyx.autoperm.service.IUserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author liyongxuan
 * @since 2022-06-09
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements IUserRoleService {

    /**
     * 根据用户id查询用户的角色列表
     *
     * @param id
     * @return java.util.List<com.lyx.autoperm.entity.Role>
     * @author 黎勇炫
     * @create 2022/6/22
     * @email 1677685900@qq.com
     */
    @Override
    public List<Role> queryRolesByUid(String id) {
        return null;
    }
}
