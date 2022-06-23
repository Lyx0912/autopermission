package com.lyx.autoperm.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.*;

/**
 * <p>
 * 
 * </p>
 *
 * @author liyongxuan
 * @since 2022-06-09
 */
@TableName("L_USER")
public class User implements Serializable, UserDetails {

    private static final long serialVersionUID = 1L;

     /**
       * 用户编号
       */
    private String id;

     /**
       * 用户名
       */
    private String username;

     /**
       * 密码
       */
    private String password;

     /**
       * 真实姓名
       */
    private String name;

     /**
       * 年龄
       */
    private String age;

     /**
       * 性别
       */
    private String sex;

     /**
       * 手机号
       */
    private String phone;

     /**
       * 家庭住址
       */
    private String address;

     /**
       * 启用状态
       */
    private Integer enable;

     /**
       * 创建时间
       */
    private String createTime;

     /**
       * 角色列表
       */
     @TableField(exist = false)
    private Set<String> roles = new HashSet<>();

    @TableField(exist = false)
    private Set<String> permissions;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

     /**
       * 默认不适用该状态
       */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * 默认不适用该状态
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * 默认不适用该状态
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enable==1?true:false;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    public void setPermissions(Set<String> permissions) {
        this.permissions = permissions;
    }

    public Set<String> getPermissions() {
        return permissions;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
    public Integer getEnable() {
        return enable;
    }

    public void setEnable(Integer enable) {
        this.enable = enable;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }
}
