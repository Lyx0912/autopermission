package com.lyx.autoperm.mapper;

import com.lyx.autoperm.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author liyongxuan
 * @since 2022-06-09
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
