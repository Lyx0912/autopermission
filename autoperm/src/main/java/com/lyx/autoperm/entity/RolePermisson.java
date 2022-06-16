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
@TableName("L_ROLE_PERMISSON")
public class RolePermisson implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private Integer roleId;

    private String permisson;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
    public String getPermisson() {
        return permisson;
    }

    public void setPermisson(String permisson) {
        this.permisson = permisson;
    }

    @Override
    public String toString() {
        return "RolePermisson{" +
            "id=" + id +
            ", roleId=" + roleId +
            ", permisson=" + permisson +
        "}";
    }
}
