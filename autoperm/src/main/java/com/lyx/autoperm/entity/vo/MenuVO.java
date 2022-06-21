package com.lyx.autoperm.entity.vo;

import java.util.List;

/**
 * @author 黎勇炫
 * @date 2022年06月20日 21:23
 */
public class MenuVO {
    /**
     * 路由名字
     */
    private String name;

    /**
     * 路由地址
     */
    private String path;

    /**
     * 是否隐藏路由，当设置 true 的时候该路由不会再侧边栏出现
     */
    private boolean hidden;

    /**
     * 重定向地址，当设置 noRedirect 的时候该路由在面包屑导航中不可被点击
     */
    private String redirect;

    /**
     * 组件地址
     */
    private String component;

    /**
     * 当你一个路由下面的 children 声明的路由大于1个时，自动会变成嵌套的模式--如组件页面
     */
    private Boolean alwaysShow;

    /**
     * 其他元素
     */
    private MetaVO meta;

    /**
     * 子路由
     */
    private List<MenuVO> children;

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getPath()
    {
        return path;
    }

    public void setPath(String path)
    {
        this.path = path;
    }

    public boolean getHidden()
    {
        return hidden;
    }

    public void setHidden(boolean hidden)
    {
        this.hidden = hidden;
    }

    public String getRedirect()
    {
        return redirect;
    }

    public void setRedirect(String redirect)
    {
        this.redirect = redirect;
    }

    public String getComponent()
    {
        return component;
    }

    public void setComponent(String component)
    {
        this.component = component;
    }

    public Boolean getAlwaysShow()
    {
        return alwaysShow;
    }

    public void setAlwaysShow(Boolean alwaysShow)
    {
        this.alwaysShow = alwaysShow;
    }

    public MetaVO getMeta()
    {
        return meta;
    }

    public void setMeta(MetaVO meta)
    {
        this.meta = meta;
    }

    public List<MenuVO> getChildren()
    {
        return children;
    }

    public void setChildren(List<MenuVO> children)
    {
        this.children = children;
    }
}
