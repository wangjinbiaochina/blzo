package com.jdkhome.blzo.common.aop.authj;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.reflect.Method;
import java.util.*;

/**
 * Created by jdk on 17/12/22.
 * Authj缓存类
 */
@Component
public class AuthjCache {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * key: uri
     * value: 权限实体
     */
    private Map<String, AuthjBean> authjCache = new HashMap<>();

    /**
     * 获取所有Authj实体的列表
     *
     * @return
     */
    public List<AuthjBean> getAuthjBeans() {
        return new ArrayList<>(authjCache.values());
    }

    public Set<String> getUris() {
        return authjCache.keySet();
    }

    /**
     * 根据方法添加权限
     *
     * @param method
     */
    public void addAuthj(Method method) {

        Class theClass = method.getDeclaringClass();

        String[] classUris = null;
        if (theClass.getAnnotation(RequestMapping.class) != null) {
            classUris = ((RequestMapping) theClass.getAnnotation(RequestMapping.class)).value();
        }
        String[] methodUris = method.getAnnotation(RequestMapping.class).value();

        if (classUris != null && classUris.length != 0) {
            Arrays.stream(classUris).forEach(classUri ->
                    Arrays.stream(methodUris).forEach(methodUri -> {
                        StringBuffer uriSb = new StringBuffer();
                        uriSb.append(classUri).append(methodUri);
                        AuthjBean authjBean = new AuthjBean(uriSb.toString(), method.getAnnotation(Authj.class));
                        authjCache.put(uriSb.toString(), authjBean);
                        logger.info("addAuthj：{}", JSONObject.toJSONString(authjBean));
                    }));
        } else {
            Arrays.stream(methodUris).forEach(methodUri -> {
                StringBuffer uriSb = new StringBuffer();
                uriSb.append(methodUri);
                AuthjBean authjBean = new AuthjBean(uriSb.toString(), method.getAnnotation(Authj.class));
                authjCache.put(uriSb.toString(), authjBean);
                logger.info("addAuthj：{}", JSONObject.toJSONString(authjBean));
            });
        }

    }

    public AuthjBean getAuthj(String uri) {
        return authjCache.get(uri);
    }


}
