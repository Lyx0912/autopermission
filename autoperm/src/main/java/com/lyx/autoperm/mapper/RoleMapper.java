package com.lyx.autoperm.mapper;

import com.lyx.autoperm.entity.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.Set;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author liyongxuan
 * @since 2022-06-09
 */
@Mapper
public interface RoleMapper extends BaseMapper<Role> {

    /**
     * 根据用户id查询角色列表
     * @param id
     * @return void
     * @author 黎勇炫
     * @create 2022/6/13
     * @email 1677685900@qq.com
     */
    Set<String> queryRolesByUserId(String id);
}
