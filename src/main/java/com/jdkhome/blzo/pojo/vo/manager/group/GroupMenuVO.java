package com.jdkhome.blzo.pojo.vo.manager.group;

import com.jdkhome.blzo.common.aop.authj.AuthjBean;

/**
 * Created by jdk on 18/1/9.
 */
public class GroupMenuVO {

    AuthjBean authjBean;

    String uri;

    Boolean have;

    public AuthjBean getAuthjBean() {
        return authjBean;
    }

    public void setAuthjBean(AuthjBean authjBean) {
        this.authjBean = authjBean;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public Boolean getHave() {
        return have;
    }

    public void setHave(Boolean have) {
        this.have = have;
    }
}
