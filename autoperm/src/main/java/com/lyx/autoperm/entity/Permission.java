package com.lyx.autoperm.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
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

    @TableField(exist = false)
    private Set<Permission> children;

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

    @Override
    public String toString() {
        return "Permission{" +
            "id=" + id +
            ", parentId=" + parentId +
            ", premName=" + permName +
            ", path=" + path +
        "}";
    }
}
