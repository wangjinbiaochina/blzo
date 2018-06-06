package com.jdkhome.blzo.common.aop.log.controller;

import java.lang.annotation.*;

/**
 * Created by fc on 17/4/20.
 */
@Documented
@Inherited
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ControllerLog {
}
