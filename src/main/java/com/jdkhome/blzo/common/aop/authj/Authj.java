package com.jdkhome.blzo.common.aop.authj;

import java.lang.annotation.*;

/**
 * Created by jdk on 17/12/22.
 * 表记该方法受到Authj 监管
 * TODO 考虑是否把 Regulatory 换成 Authj 这样好看些。。
 */
@Inherited
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Authj {

    /**
     * value 其实是标记该接口的唯一标识
     * /user/list 这样
     * @return
     */
    String value();

    /**
     * 标记该接口是否需要鉴权
     * 默认是
     * @return
     */
    boolean auth() default true;

    /**
     * 标记该接口是否可作为一个菜单
     * 默认否
     * @return
     */
    boolean menu() default false;


}
