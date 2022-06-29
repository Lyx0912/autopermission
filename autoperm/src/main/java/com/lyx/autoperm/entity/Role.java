package com.lyx.autoperm.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
    /**
     * 状态
     */
    private Integer status;
    /**
     * 创建时间
     */
    private String createTime;

     /**
       * 菜单列表
       */
    @TableField(exist = false)
    private List<String> menuList = new ArrayList<>();

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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public List<String> getMenuList() {
        return menuList;
    }

    public void setMenuList(List<String> menuList) {
        this.menuList = menuList;
    }
}
