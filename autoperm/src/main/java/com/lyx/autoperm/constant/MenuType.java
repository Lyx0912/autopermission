package com.lyx.autoperm.constant;

/**
 * @author 黎勇炫
 * @date 2022年06月21日 11:17
 */
public enum MenuType {

    DIR(0,"目录"),
    MENU(1,"菜单"),
    BUTTON(2,"按钮");

    private Integer code;
    private String info;

    MenuType(Integer code, String info) {
        this.code = code;
        this.info = info;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
