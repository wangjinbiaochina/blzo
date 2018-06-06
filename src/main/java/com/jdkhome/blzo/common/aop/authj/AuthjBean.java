package com.jdkhome.blzo.common.aop.authj;

/**
 * Created by jdk on 17/12/22.
 * 权限对象
 */
public class AuthjBean {

    /**
     * 代表URI
     */
    String uri;

    /**
     * 权限对象名称
     */
    String name;

    /**
     * 是否需要鉴权
     */
    Boolean auth;

    /**
     * 是否可作为菜单
     */
    Boolean menu;

    public AuthjBean(String uri, Authj authj) {
        this.uri = uri;
        this.name = authj.value();
        this.auth = authj.auth();
        this.menu = authj.menu();
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getAuth() {
        return auth;
    }

    public void setAuth(Boolean auth) {
        this.auth = auth;
    }

    public Boolean getMenu() {
        return menu;
    }

    public void setMenu(Boolean menu) {
        this.menu = menu;
    }
}
