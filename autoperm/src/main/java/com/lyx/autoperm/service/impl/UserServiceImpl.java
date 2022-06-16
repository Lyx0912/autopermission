package com.lyx.autoperm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lyx.autoperm.entity.User;
import com.lyx.autoperm.exception.UserCodeEnum;
import com.lyx.autoperm.exception.UserException;
import com.lyx.autoperm.mapper.PermissionMapper;
import com.lyx.autoperm.mapper.UserMapper;
import com.lyx.autoperm.service.IUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

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
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService, UserDetailsService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private PermissionMapper permissionMapper;

    /**
     * 用户登录
     * @return void
     * @author 黎勇炫
     * @create 2022/6/10
     * @email 1677685900@qq.com
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        // 校验用户名密码是否为空
        if(StringUtils.isEmpty(username)) throw new UserException(UserCodeEnum.USERNAME_IS_EMPTY);
        // 查询用户
        User userFromDB = userMapper.selectOne(new QueryWrapper<User>().eq("username", username));
        // 判断用户是否为空或账号不存在
        if(null == userFromDB){
            throw new UsernameNotFoundException("用户不存在");
        }
        // 查询登录用户的权限列表
        Set<String> permissions = permissionMapper.queryPermissions(userFromDB.getId());
        if(!CollectionUtils.isEmpty(permissions)){
            userFromDB.setPermissions(permissions);
        }

        return userFromDB;
    }
}
