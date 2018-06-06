package com.jdkhome.blzo.common.aop.authj;

import java.util.List;

/**
 * Created by jdk on 18/1/8.
 * 菜单VO
 */
public class MenuVO {
    String groupName;
    List<AuthjBean> authjBeans;

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public List<AuthjBean> getAuthjBeans() {
        return authjBeans;
    }

    public void setAuthjBeans(List<AuthjBean> authjBeans) {
        this.authjBeans = authjBeans;
    }
}
