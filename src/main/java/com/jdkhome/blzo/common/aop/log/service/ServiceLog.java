package com.jdkhome.blzo.common.aop.log.service;

import java.lang.annotation.*;

/**
 * Created by zzhoo8 on 2017/9/8.
 */
@Documented
@Inherited
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ServiceLog {

    String value();

}
