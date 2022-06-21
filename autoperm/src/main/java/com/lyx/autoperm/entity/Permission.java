package com.lyx.autoperm.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * <p>
 * 
 * </p>
 *
 * @author liyongxuan
 * @since 2022-06-09
 */
@TableName("L_PERMISSON")
public class Permission implements Serializable {

    private static final long serialVersionUID = 1L;

     /**
       * 权限编号
       */
    private Integer id;

     /**
       * 父权限编号
       */
    private Integer parentId;

     /**
       * 权限名称
       */
    private String permName;

     /**
       * 权限路径
       */
    private String path;

     /**
       * 排序
       */
    private Integer sort;

     /**
       * 图标
       */
     private String icon;
      /**
        * 菜单类型
        */
     private Integer type;

      /**
        * 组件地址
        */
     private String component;

    @TableField(exist = false)
    private Set<Permission> children = new HashSet<>();;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getPermName() {
        return permName;
    }

    public void setPremName(String premName) {
        this.permName = premName;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Set<Permission> getChildren() {
        return children;
    }

    public void setChildren(Set<Permission> children) {
        this.children = children;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public void setPermName(String permName) {
        this.permName = permName;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component;
    }
}
