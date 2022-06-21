package com.lyx.autoperm.entity.vo;/**
 *
 *@author 黎勇炫
 *@date 2022年06月20日 21:24
 */
public class MetaVO {

    public MetaVO(String title, String icon) {
        this.title = title;
        this.icon = icon;
    }

    /**
     * 设置该路由在侧边栏和面包屑中展示的名字
     */
    private String title;

    /**
     * 设置该路由的图标，对应路径src/assets/icons/svg
     */
    private String icon;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
