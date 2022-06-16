package com.lyx.autoperm.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author liyongxuan
 * @since 2022-06-09
 */
@TableName("L_ROLE")
public class Role implements Serializable {

    private static final long serialVersionUID = 1L;

     /**
       * 角色编号
       */
    private Integer id;

     /**
       * 角色名称
       */
    private String roleName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public String toString() {
        return "Role{" +
            "id=" + id +
            ", roleName=" + roleName +
        "}";
    }
}
