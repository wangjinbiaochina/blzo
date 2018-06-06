package com.jdkhome.blzo.common.aop.authj;

import java.util.List;
import java.util.Set;

/**
 * Created by jdk on 18/1/6.
 * 用户权限配置实体
 */
public class UserAuthjConfBean {

    Integer id;

    String name;

    Set<String> authUriSet;

    /**
     * 第一版 菜单没有分级= =。
     * 考虑做一个组菜单并且带层级的关系表
     */
    List<MenuVO> menuList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<String> getAuthUriSet() {
        return authUriSet;
    }

    public void setAuthUriSet(Set<String> authUriSet) {
        this.authUriSet = authUriSet;
    }

    public List<MenuVO> getMenuList() {
        return menuList;
    }

    public void setMenuList(List<MenuVO> menuList) {
        this.menuList = menuList;
    }
}
