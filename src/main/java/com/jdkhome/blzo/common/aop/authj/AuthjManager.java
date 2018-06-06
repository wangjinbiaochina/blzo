package com.jdkhome.blzo.common.aop.authj;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by jdk on 18/1/6.
 */
@Component
public class AuthjManager {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    AuthjCache authjCache;

    @Autowired
    AuthjService authjService;

    /**
     * SESSION 中的key
     */
    public static final String AUTHJ_KEY = "authj_key";


    /**
     * 获取request
     *
     * @return
     */
    private HttpServletRequest getRequest() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes()).getRequest();
        return request;
    }


    /**
     * 获取登录的用户id，没登录则返回0
     *
     * @return
     */
    public Integer getUserId() {
        UserAuthjConfBean userAuthjConfBean = this.getUserAuthjConfBean();
        if (userAuthjConfBean == null) {
            return 0;
        } else {
            return userAuthjConfBean.getId();
        }
    }

    /**
     * 获取登录的用户name，没登录则返回null
     *
     * @return
     */
    public String getUserName() {
        UserAuthjConfBean userAuthjConfBean = this.getUserAuthjConfBean();
        if (userAuthjConfBean == null) {
            return null;
        } else {
            return userAuthjConfBean.getName();
        }
    }

    /**
     * 获取登录用户的权限实体
     *
     * @return
     */
    public UserAuthjConfBean getUserAuthjConfBean() {
        return (UserAuthjConfBean) this.getRequest().getSession().getAttribute(AUTHJ_KEY);
    }


    /**
     * 鉴权
     *
     * @return
     */
    public boolean authentication(String uri) {

        // 权限实体
        AuthjBean authjBean = authjCache.getAuthj(uri);
        if (authjBean == null) {
            logger.error("[Authj - 鉴权] uri:{} -> authjCache未命中,请检查");
            return true;
        }
        logger.debug("[Authj - 鉴权] uri:{} -> authjBean:{}", uri, JSONObject.toJSONString(authjBean));


        if (!authjBean.getAuth()) {
            logger.info("[Authj - 鉴权] uri:{} -> 无需鉴权", uri);
            return true;
        }

        // 用户权限集合
        UserAuthjConfBean userAuthjConfBean = this.getUserAuthjConfBean();

        if (userAuthjConfBean == null) {
            logger.info("[Authj - 鉴权] 用户未登录");
            return false;
        }

        if (!userAuthjConfBean.getAuthUriSet().contains(uri)) {
            logger.info("[Authj - 鉴权] 用户:{}({})没有权限:{}", userAuthjConfBean.getName(), userAuthjConfBean.getId(), uri);
            return false;
        }

        return true;
    }

    /**
     * 授权
     *
     * @param userId
     * @return
     */
    public UserAuthjConfBean grant(Integer userId) {
        //入参检验。。
        if (userId == null || userId == 0) {
            return null;
        }

        UserAuthjConfBean userAuthjConfBean = authjService.getUserAuthjConf(userId);
        this.getRequest().getSession().setAttribute(AUTHJ_KEY, userAuthjConfBean);
        return userAuthjConfBean;
    }

    /**
     * 解除授权
     */
    public void delGrant() {
        this.getRequest().getSession().removeAttribute(AUTHJ_KEY);
    }

}
